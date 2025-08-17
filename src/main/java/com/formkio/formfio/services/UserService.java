package com.formkio.formfio.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.InvalidJWT;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.UsersTable;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.SignedJWT;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;


@Service
public class UserService {

    private final UsersTable usersTable;
    private final StripeService stripeService;
    private final JSONService jsonParserService;

    @Value("${spring.jwk.uri}")
    private String JWK;

    public UserService(UsersTable usersTable, StripeService stripeService, JSONService jsonService) {
        this.usersTable = usersTable;
        this.stripeService = stripeService;
        this.jsonParserService = jsonService;
    }

    public void updateEmail(UsersModel usersModel, String newEmail) {
        UsersModel user = usersTable.getUserByEmail(usersModel.getEmail());
        usersTable.updateEmail(usersModel, newEmail);
        System.out.println(user.getStripeID());
        stripeService.updateUserEmail(user.getStripeID(), newEmail);
    }

    /**
     * Given a users model with only the email, it will fill the information of the user in the
     * SQL database into the usersModel.
     *
     * @param usersModel
     */
    public void grabUser(UsersModel usersModel) {
        usersTable.getUserByEmail(usersModel);
    }

    public void updateAccountPlan(UsersModel usersModel, String plan) {
        System.out.println(getAccountPlan(plan));
        usersTable.updateUserAccountPlan(usersModel, getAccountPlan(plan));

    }

    public void save(UsersModel usersModel) {
        Customer customer = stripeService.createUser(usersModel);
        System.out.println(customer.toString());

        usersTable.createNewUser(usersModel, customer);
    }

    /**
     * Parses the JWT token and returns a user model that contains
     * the email of the user contained in the JWT.
     *
     * @param jwt
     * @return UsersModel
     */
    public UsersModel parseJWT(String jwt) {
        jwt = jwt.split(" ")[1];
        SignedJWT signedJWT;

        try {
            signedJWT = SignedJWT.parse(jwt);
        } catch (ParseException e) {
            System.out.println("UsersModel parseJWT(String) parse: " + e);
            throw new InvalidJWT("Invalid Access Token. Please reauthenticated.");
        }

        try {
            JWSVerifier verifier = new ECDSAVerifier(getVerifier());
            boolean isVerified = verifier.verify(signedJWT.getHeader(), signedJWT.getSigningInput(), signedJWT.getSignature());

            if (isVerified) {
                Payload payload = signedJWT.getPayload();
                Map<String, Object> user = payload.toJSONObject();
            }
        } catch (JOSEException e) {
            // TODO get better error
            System.out.println("UsersModel parseJWT(String) verify: " + e);
            throw new InternalError();
        }

        Map<String, Object> userData = signedJWT.getPayload().toJSONObject();
        UsersModel usersModel = new UsersModel();
        usersModel.setEmail(userData.get("email").toString());
        // aud

        return usersModel;

    }

    private ECKey getVerifier() {
        JsonNode key = getJWKVerifier();

        Base64URL x = new Base64URL(key.get("x").toString());
        Base64URL y = new Base64URL(key.get("y").toString());
        String keyID = key.get("kid").toString();

        return new ECKey.Builder(Curve.P_256, x, y)
                .keyID(keyID)
                .keyUse(KeyUse.SIGNATURE)
                .build();
    }

    private JsonNode getJWKVerifier() {
        HttpURLConnection conn;
        try {
            URI uri = new URI(JWK);
            URL url = uri.toURL();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (URISyntaxException e) {
            // TODO make better ERROR
            System.out.println("JsonNode getJWKVerifier() URISyntax: " + e);
            throw new InternalError();
        } catch (IOException e) {
            System.out.println("JsonNode getJWKVerifier() openConnection: " + e);
            throw new InternalError();
        }

        // TODO if statusCode is not 200 (it usually is), we should handle that.

        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                data.append(line);
            }
        } catch (IOException e) {
            System.out.println("JsonNode getJWKVerifier() readJWKMsg: " + e);
            throw new InternalError();
        }

        JsonNode tree = jsonParserService.parseJson(data.toString());
        JsonNode keys = tree.get("keys");
        // We only have 1 key right now.
        return keys.get(0);
    }


    public int getAccountPlan(String plan) {
        switch (plan.toLowerCase()) {
            case "solo":
                return 1;
            case "team":
                return 2;
            case "business":
                return 3;
            default:
                System.out.println("Unknow plan");
                return 0;
        }
    }
}
