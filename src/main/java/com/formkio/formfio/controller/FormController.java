package com.formkio.formfio.controller;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.JSONService;
import com.formkio.formfio.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
// This is here for dev since i was writing to things on a file.
public class FormController {

    UserService userService;
    FormService formService;
    JSONService jsonService;

    public FormController(UserService userService, FormService formService, JSONService jsonService) {
        this.userService = userService;
        this.formService = formService;
        this.jsonService = jsonService;
    }

    @Value("${dev.server}") String devSer;
    @GetMapping("/forms/test")
    @CrossOrigin(origins = "*")
    public String test() {
        System.out.println(devSer);

        return "you reached me";
    }

    // CrossOrigin will be the frontend website in the future
    @PostMapping("/forms/api/create")
    @CrossOrigin(value = "${dev.server}")
    public String createForm(@RequestHeader Map<String, String> header, @RequestBody Map<String, String> data) {
        UsersModel usersModel = userService.parseJWT(header.get("authorization"));
        userService.grabUser(usersModel);
        // use the form repo to create a new endpoint and form

        FormsDTO formsDTO = formService.createFormsDTO(usersModel, data);
        formService.save(formsDTO);

        // return the form creation status & the new api url they will use
        return "success";
    }

    @GetMapping("forms/api/get")
    @CrossOrigin(value = "${dev.server}")
    public Set<String> getForm(@RequestHeader Map<String, String> header) {
        UsersModel usersModel = userService.parseJWT(header.get("authorization"));
        userService.grabUser(usersModel);

        List<FormsDTO> formsDTOS = formService.getForm(usersModel);
        return Collections.singleton(jsonService.jsonStringify(formsDTOS));
    }

}

