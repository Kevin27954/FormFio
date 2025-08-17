package com.formkio.formfio.services;

import com.formkio.formfio.dto.StripeProductDTO;
import com.formkio.formfio.model.UsersModel;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.ProductData;
import com.stripe.param.checkout.SessionRetrieveParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${STRIPE_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public void updateUserEmail(String stripeID, String newEmail) {
        System.out.println(stripeID + " " + newEmail);

        try {
            Customer resource = Customer.retrieve(stripeID);
            CustomerUpdateParams params = CustomerUpdateParams.builder()
                    .setEmail(newEmail)
                    .setName(newEmail)
                    .build();
            Customer customer = resource.update(params);
            System.out.println(customer.getEmail());
            System.out.println(customer.getName());
        } catch (StripeException e) {
            // TODO GIVE ME A BETTER ERROR
            System.out.println("void updateUserEmail: " + e);
            throw new RuntimeException("Unable to get/update stripe customer");
        }
    }

    public StripeProductDTO getStripeProductInfo(String product) {
        return switch (product) {
            case "solo" -> new StripeProductDTO("solo", 500L);
            case "team" -> new StripeProductDTO("team", 1500L);
            case "business" -> new StripeProductDTO("business", 5000L);
            default -> throw new RuntimeException("Unknown product");
        };
    }

    public Session getSessionStatus(String sessionId) {
        RequestOptions options = RequestOptions.builder().build();
        SessionRetrieveParams params = SessionRetrieveParams.builder()
                .addExpand("payment_intent")
                .addExpand("line_items")
                .addExpand("line_items.data.price.product")
                .build();

        try {

            return Session.retrieve(sessionId, params, options);
        } catch (StripeException e) {
            // TODO GIVE ME A BETTER ERROR
            throw new RuntimeException(e);
        }
    }

    public Customer createUser(UsersModel user) {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(user.getEmail())
                .setEmail(user.getEmail())
                .build();

        try {
            return Customer.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    public LineItem createSubscriptionItem(String currency, Long price, String name) {
        return LineItem.builder()
                .setQuantity(1L)
                .setPriceData(
                        createSubscriptionPriceData(currency, price,
                                createSubscriptionProductData(name))
                )
                .build();
    }

    private PriceData createSubscriptionPriceData(String currency, Long price, ProductData productData) {
        return PriceData.builder()
                .setCurrency(currency)
                .setUnitAmount(price)
                .setProductData(productData)
                .build();
    }

    private ProductData createSubscriptionProductData(String name) {
        return ProductData.builder()
                .setName(name)
                .build();
    }

}
