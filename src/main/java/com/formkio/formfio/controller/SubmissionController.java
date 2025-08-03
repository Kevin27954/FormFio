package com.formkio.formfio.controller;

import com.formkio.formfio.exceptions.NotValidForm;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SubmissionController {

    // Rate limitor now


    private final FormService formService;
    private final SubmissionService submissionService;

    @Value("${spring.jwk.uri}")
    private String JWK;

    public SubmissionController(FormService formService, SubmissionService submissionService) {
        this.formService = formService;
        this.submissionService = submissionService;
    }

    @PostMapping(value = "/{endpoint}", consumes = "application/x-www-form-urlencoded")
    public String acceptSubmission(@PathVariable String endpoint, @RequestParam Map<String, String> submission, HttpServletRequest request) {
        if (!formService.getEndpoint(endpoint)) {
            throw new NotValidForm("Form endpoint < " + endpoint + " > is not valid.");
        }

        String ipAddr = request.getRemoteAddr();
        submissionService.processSubmission(endpoint, submission, ipAddr);

        return "success";
    }

}
