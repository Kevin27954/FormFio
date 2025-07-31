package com.formkio.formfio.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.JSONService;
import com.formkio.formfio.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;
    private JSONService jsonService;

    public UserController(UserService userService, JSONService jsonService) {
        this.userService = userService;
        this.jsonService = jsonService;
    }

    // Will be the website in the future for cross origin.
    @PostMapping("/register")
    @CrossOrigin(value = "localhost:5173")
    public void registerUser(@RequestBody String data) {
        JsonNode user = jsonService.parseJson(data);

        UsersModel usersModel = new UsersModel();
        usersModel.setEmail(user.get("email").toString().replace("\"", ""));

        userService.save(usersModel);
        // TODO they should be redirected to verify the thing
    }
}
