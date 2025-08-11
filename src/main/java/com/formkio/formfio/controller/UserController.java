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

        System.out.println(stripeData.toString());
    }

    @PostMapping("/users/api/update/plan")
    @CrossOrigin(value = "${dev.server}")
    public void updateUserPlan(@RequestBody String data) {
        JsonNode stripeData = jsonService.parseJson(data);

        System.out.println(stripeData.toString());
    }
}
