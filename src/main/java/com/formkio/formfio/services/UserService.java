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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;


@Component
public class UserService {

    private UsersTable usersTable;
    private JSONParserService jsonParserService;

    @Value("${spring.jwk.uri}")
    private String JWK;

    public UserService(UsersTable usersTable, JSONParserService jsonParserService) {
        this.usersTable = usersTable;
        this.jsonParserService = jsonParserService;
    }

    /**
     * Given a users model, it will fill the information of the user in the
     * SQL database into the usersModel.
     *
     * @param usersModel
     */
    public void grabUser(UsersModel usersModel) {

    }

    public void save(UsersModel usersModel) {
        usersTable.createNewUser(usersModel);
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
            System.out.println(isVerified);

            if (isVerified) {
                Payload payload = signedJWT.getPayload();
                Map<String, Object> user = payload.toJSONObject();
                System.out.println(user);
            }
        } catch (JOSEException e) {
            // TODO get better error
            System.out.println("UsersModel parseJWT(String) verify: " + e);
            throw new InternalError();
        }

        System.out.println(signedJWT.getPayload().toJSONObject().toString());
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

}
