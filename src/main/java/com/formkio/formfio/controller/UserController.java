package com.formkio.formfio.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.JSONService;
import com.formkio.formfio.services.StripeService;
import com.formkio.formfio.services.UserService;
import com.stripe.model.Customer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;
    private JSONService jsonService;

    public UserController(UserService userService, JSONService jsonService) {
        this.userService = userService;
        this.jsonService = jsonService;
    }

    // Will be the website in the future for cross origin.
    @PostMapping("/users/api/register")
    @CrossOrigin(value = "${dev.server}")
    public void registerUser(@RequestBody String data) {
        JsonNode userData = jsonService.parseJson(data);

        UsersModel user = new UsersModel();
        user.setEmail(userData.get("email").toString().replace("\"", ""));

        userService.save(user);
        // TODO they should be redirected to verify the thing
    }

    @PostMapping("/users/api/update/email")
    @CrossOrigin(value = "${dev.server}")
    public void updateUserEmail(@RequestBody String data) {
        JsonNode stripeData = jsonService.parseJson(data);

        // It should update email across 3 places:
        // This should never happen though if they are verified.
        // So SUPABASE can be done in frontend
        // For STRIPE, I just need to grab the stripe_id, I currently have and update that. This is the backend.
        // My db, supabase, and stripe

        // Perhaps store a is verified thing in this too?

        System.out.println(stripeData.toString());
    }

}
