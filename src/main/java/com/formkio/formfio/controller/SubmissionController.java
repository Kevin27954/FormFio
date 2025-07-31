package com.formkio.formfio.controller;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.LimitPassedError;
import com.formkio.formfio.exceptions.NotValidForm;
import com.formkio.formfio.services.FormService;
import com.formkio.formfio.services.JedisLimiterService;
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
    private final JedisLimiterService jedisLimiterService;

    @Value("${spring.jwk.uri}")
    private String JWK;

    public SubmissionController(FormService formService, SubmissionService submissionService, JedisLimiterService jedisLimiterService) {
        this.formService = formService;
        this.submissionService = submissionService;
        this.jedisLimiterService = jedisLimiterService;
    }

    @PostMapping(value = "/{endpoint}", consumes = "application/x-www-form-urlencoded")
    public String acceptSubmission(@PathVariable String endpoint, @RequestParam Map<String, String> submission, HttpServletRequest request) {
        if (!formService.getEndpoint(endpoint)) {
            throw new NotValidForm("Form endpoint < " + endpoint + " > is not valid.");
        }

        if (!jedisLimiterService.isAllowed(endpoint)) {
            throw new LimitPassedError("You've hit Your Maximum Limit.");
        }

        String submissionJson = submissionService.toJson(submission);
        String ip_addr = request.getRemoteAddr();
        SubmissionDTO submissionDTO = new SubmissionDTO(submissionJson, "web", ip_addr, endpoint);
        submissionService.save(submissionDTO);

        return "success";
    }

}
