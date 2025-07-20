package com.formkio.formfio.controller;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.NotValidForm;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SubmissionController {

    private final FormService formService;
    private final SubmissionService submissionService;

    public SubmissionController(FormService formService, SubmissionService submissionService) {
        this.formService = formService;
        this.submissionService = submissionService;
    }

    @PostMapping(value = "/{endpoint}", consumes = "application/x-www-form-urlencoded")
    public String acceptSubmission(@PathVariable String endpoint, @RequestParam Map<String, String> submission, HttpServletRequest request) {
        if (!formService.getEndpoint(endpoint)) {
            throw new NotValidForm("Form endpoint < " + endpoint + " > is not valid.");
        }

        String submissionJson = submissionService.toJson(submission);
        String ip_addr = request.getRemoteAddr();
        SubmissionDTO submissionDTO = new SubmissionDTO(submissionJson, "web", ip_addr, endpoint);
        submissionService.save(submissionDTO);

        return "success";
    }

}
