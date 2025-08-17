package com.formkio.formfio.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.JSONService;
import com.formkio.formfio.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public String updateUserEmail(@RequestHeader Map<String, String> header, @RequestBody String body) {
        JsonNode data = jsonService.parseJson(body);
        String email = data.get("email").toString().replace("\"", "");

        UsersModel usersModel = userService.parseJWT(header.get("authorization"));
        userService.grabUser(usersModel);

        userService.updateEmail(usersModel, email);

        return jsonService.jsonStringify("success");

        // It should update email across 3 places:
        // This should never happen though if they are verified.
        // So SUPABASE can be done in frontend
        // For STRIPE, I just need to grab the stripe_id, I currently have and update that. This is the backend.
        // My db, supabase, and stripe

        // Perhaps store a is verified thing in this too?

    }

}
