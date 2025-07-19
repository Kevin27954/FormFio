package com.formkio.formfio.controller;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.form_submission_errors.NotValidForm;
import com.formkio.formfio.repository.EndpointsTable;
import com.formkio.formfio.services.FormSubmissionFactory;
import com.formkio.formfio.services.SubmissionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SubmissionController {

    private final FormSubmissionFactory formSubmissionFactory;
    private final EndpointsTable endpointsTable;
    private final SubmissionService submissionService;

    public SubmissionController(FormSubmissionFactory formSubmissionFactory, EndpointsTable endpointsTable, SubmissionService submissionService) {
        this.formSubmissionFactory = formSubmissionFactory;
        this.endpointsTable = endpointsTable;
        this.submissionService = submissionService;
    }

    // So my way of thinking of how form was incorrect. Form can be basically anything.
    // So I have to accept any fields and not have anything set static.
    // In the end it would just be me parsing the form and then storing the entire thing as a json.
    // Nothing else matters. However, it is not certain that it is in json so I have to grab it
    // first and then convert it into Json.
    @PostMapping(value = "/{endpoint}", consumes = "application/x-www-form-urlencoded")
    public String acceptSubmission(@PathVariable String endpoint, @RequestParam Map<String, String> formData) {
        if (endpointsTable.getEndpoint(endpoint)) {
            throw new NotValidForm("Form endpoint" + endpoint + " is not valid ");
        }

        String submissionJson = submissionService.toJson(formData);
        //  There will be a function to greb the ip addr from the request header
        String ip_addr = "null";
        SubmissionDTO submissionDTO = new SubmissionDTO(submissionJson, "web", "", endpoint);
        submissionService.save(submissionDTO);

        return "success";
    }

}
