package com.formkio.formfio.controller;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.NotValidForm;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.JSONService;
import com.formkio.formfio.services.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SubmissionController {

    private final FormService formService;
    private final SubmissionService submissionService;
    private final JSONService jsonService;

    @Value("${spring.jwk.uri}")
    private String JWK;

    public SubmissionController(FormService formService, SubmissionService submissionService, JSONService jsonService) {
        this.formService = formService;
        this.submissionService = submissionService;
        this.jsonService = jsonService;
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

    @GetMapping(value="/{endpoint}")
    @CrossOrigin(value = "${dev.server}")
    public String getSubmissions(@PathVariable String endpoint, @RequestParam Map<String, String> params) {
        if (!formService.getEndpoint(endpoint)) {
            throw new NotValidForm("Form endpoint < " + endpoint + " > is not valid.");
        }

        List<SubmissionDTO> result = submissionService.getSubmissions(endpoint, params);
        return jsonService.jsonStringify(result);
    }

}
