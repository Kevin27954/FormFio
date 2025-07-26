package com.formkio.formfio.controller;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
// This is here for dev since i was writing to things on a file.
public class FormController {

    UserService userService;
    FormService formService;

    public FormController(UserService userService, FormService formService) {
        this.userService = userService;
        this.formService = formService;
    }

    // We need to know what kind of user mangaement I am doing but for now,
    // Assume that it is just a basic one. We just use basic shit like default values
    // Emails are expected to be unique.
    @PostMapping("/forms/api/create")
    @CrossOrigin(value = "*")
    public String createForm(@RequestHeader Map<String, String> header, @RequestBody Map<String, String> data) {
        UsersModel usersModel = userService.parseJWT(header.get("authorization"));
        userService.grabUser(usersModel);
        // use the form repo to create a new endpoint and form

        FormsDTO formsDTO = formService.createFormsDTO(usersModel, data);
        formService.save(formsDTO);

        // return the form creation status & the new api url they will use
        return "success";
    }

}
