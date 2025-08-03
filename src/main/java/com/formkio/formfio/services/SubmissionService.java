package com.formkio.formfio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.FormSubmissionError;
import com.formkio.formfio.exceptions.LimitPassedError;
import com.formkio.formfio.exceptions.MalformDataError;
import com.formkio.formfio.repository.SubmissionTable;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubmissionService {

    private DBDriver dbDriver;
    private SubmissionTable submissionTable;

    private final JedisLimiterService jedisLimiterService;
    private final EmailService emailService;

    private final String INSERT_SUBMISSION = "INSERT INTO submissions";

    public SubmissionService(DBDriver dbDriver,SubmissionTable submissionTable, JedisLimiterService jedisLimiterService, EmailService emailService) {
        this.dbDriver = dbDriver;
        this.submissionTable = submissionTable;
        this.jedisLimiterService = jedisLimiterService;
        this.emailService = emailService;
    }

    Map<String, String> extractFormFields(Map<String, String> submission) {
        return null;
    }

    public void save(SubmissionDTO submissionDTO) throws FormSubmissionError {
        // Extra logic here if needed
        submissionTable.createNewFormSubmission(submissionDTO);
    }

    public String toJson(Map<String, String> submission) throws FormSubmissionError {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(submission);
        } catch (JsonProcessingException e) {
            System.out.println("String toJson():" + e);
            throw new MalformDataError("Unable to read form submission.");
        }
    }

    public void processSubmission(String endpoint, Map<String,String> submission, String ipAddr) {
        if (!jedisLimiterService.isAllowed(endpoint)) {
            throw new LimitPassedError("You've hit Your Maximum Limit.");
        }

        // TODO impleemnt this function in the future
        this.emailService.sendEmail();

        String submissionJson = toJson(submission);
        SubmissionDTO submissionDTO = new SubmissionDTO(submissionJson, "web", ipAddr, endpoint);
        save(submissionDTO);
    }
}
