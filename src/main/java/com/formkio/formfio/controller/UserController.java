package com.formkio.formfio.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.JSONParserService;
import com.formkio.formfio.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {


    private UserService userService;
    private JSONParserService jsonParserService;

    public UserController(UserService userService, JSONParserService jsonParserService) {
        this.userService = userService;
        this.jsonParserService = jsonParserService;
    }

    // Will be the website in the future for cross origin.
    @PostMapping("/register")
    @CrossOrigin(value = "*")
    public void registerUser(@RequestBody String data) {
        JsonNode user = jsonParserService.parseJson(data);

        UsersModel usersModel = new UsersModel();
        usersModel.setEmail(user.get("email").toString().replace("\"", ""));

        userService.save(usersModel);
        // TODO they should be redirected to verify the thing
    }
}
