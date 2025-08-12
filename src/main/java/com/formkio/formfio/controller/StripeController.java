package com.formkio.formfio.controller;

import com.formkio.formfio.dto.StripeProductDTO;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.StripeService;
import com.formkio.formfio.services.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StripeController {

    private final StripeService stripeService;
    private final UserService userService;

    public StripeController(StripeService stripeService, UserService userService) {
        this.stripeService = stripeService;
        this.userService = userService;
    }

    @PostMapping("/create/checkout/{product}")
    public Map<String, String> createCheckoutSession(@PathVariable String product) {
        StripeProductDTO productDTO = stripeService.getStripeProductInfo(product);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setUiMode(SessionCreateParams.UiMode.CUSTOM)
                .setReturnUrl("http://localhost:5173/complete?session_id={CHECKOUT_SESSION_ID}")
                .addLineItem(stripeService.createSubscriptionItem("usd", productDTO.getPrice(), productDTO.getName()))
                .build();

        Session session;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            // TODO Create a better error here please
            throw new RuntimeException(e);
        }

        Map<String, String> map = new HashMap<>();
        map.put("checkoutSessionClientSecret", session.getRawJsonObject().getAsJsonPrimitive("client_secret").getAsString());
        return map;
    }

    @PostMapping("/fulfill/{session_id}")
    public Map<String, String> fulfillOrder(@PathVariable String session_id, @RequestHeader Map<String, String> header) throws StripeException {
        UsersModel usersModel = userService.parseJWT(header.get("authorization"));
        userService.grabUser(usersModel);

        System.out.println(session_id);
        Session session = stripeService.getSessionStatus(session_id);

        Map<String, String> map = new HashMap<>();
        map.put("status", session.getRawJsonObject().getAsJsonPrimitive("status").getAsString());
        map.put("payment_status", session.getRawJsonObject().getAsJsonPrimitive("payment_status").getAsString());
        map.put("payment_intent_id", session.getRawJsonObject().getAsJsonObject("payment_intent").getAsJsonPrimitive("id").getAsString());
        map.put("payment_intent_status", session.getRawJsonObject().getAsJsonObject("payment_intent").getAsJsonPrimitive("status").getAsString());

        String productId = session.getLineItems().getData().getFirst().getPrice().getProduct();
        // TODO HANDLE THIS ERROR PROPERLY PLEASE
        Product product = Product.retrieve(productId);
        userService.updateAccountPlan(usersModel, product.getName());

        return map;
    }

}
