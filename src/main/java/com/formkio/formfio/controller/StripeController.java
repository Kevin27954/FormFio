package com.formkio.formfio.controller;

import com.formkio.formfio.dto.StripeProductDTO;
import com.formkio.formfio.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create/checkout/{product}")
    public Map<String, String> createCheckoutSession(@PathVariable String product) {
        StripeProductDTO productDTO = stripeService.getStripeProductInfo(product);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setUiMode(SessionCreateParams.UiMode.CUSTOM)
                .setReturnUrl("http://localhost:5173")
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

}
