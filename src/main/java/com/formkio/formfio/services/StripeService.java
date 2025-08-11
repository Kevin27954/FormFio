package com.formkio.formfio.services;

import com.formkio.formfio.dto.StripeProductDTO;
import com.formkio.formfio.model.UsersModel;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.ProductData;
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

    public StripeProductDTO getStripeProductInfo(String product) {
        return switch (product) {
            case "solo" -> new StripeProductDTO("Solo Tier", 500L);
            case "team" -> new StripeProductDTO("Small Team Tier", 1500L);
            case "business" -> new StripeProductDTO("Business Tier", 5000L);
            default -> throw new RuntimeException("Unknown product");
        };
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
