package com.formkio.formfio.controller;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.EndpointsTable;
import com.formkio.formfio.repository.FormsTable;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
// This is here for dev since i was writing to things on a file.
public class FormController {

    UserService userService;
    FormsTable formsTable;
    EndpointsTable endpointsTable;

    public FormController(UserService userService, FormsTable formsTable, EndpointsTable endpointsTable) {
        this.userService = userService;
        this.formsTable = formsTable;
        this.endpointsTable = endpointsTable;
    }

    // We need to know what kind of user mangaement I am doing but for now,
    // Assume that it is just a basic one. We just use basic shit like default values
    // Emails are expected to be unique.
    @PostMapping("/forms/api/create")
    @CrossOrigin(value = "*")
    public String createForm(@RequestBody Map<String, String> data) {
        UsersModel usersModel = userService.getUserInfo();
        // use the form repo to create a new endpoint and form
        String endpoint = endpointsTable.createNewEndpoint();
        FormsDTO formsDTO = new FormsDTO(usersModel).parse(data).setEndpoint(endpoint);
        formsTable.createNewForm(usersModel, formsDTO);
        // return the form creation status & the new api url they will use
        return "success";
    }

}
