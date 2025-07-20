package com.formkio.formfio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.FormSubmissionInternalError;
import com.formkio.formfio.repository.SubmissionTable;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubmissionService {

    private DBDriver dbDriver;
    private SubmissionTable submissionTable;

    private final String INSERT_SUBMISSION = "INSERT INTO submissions";

    public SubmissionService(DBDriver dbDriver,SubmissionTable submissionTable) {
        this.dbDriver = dbDriver;
        this.submissionTable = submissionTable;
    }

    Map<String, String> extractFormFields(Map<String, String> submission) {
        return null;
    }

    public void save(SubmissionDTO submissionDTO) throws FormSubmissionInternalError {
        // Extra logic here if needed
        submissionTable.createNewFormSubmission(submissionDTO);
    }

    public String toJson(Map<String, String> submission) throws FormSubmissionInternalError {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(submission);
        } catch (JsonProcessingException e) {
            System.out.println("String toJson():" + e);
            throw new FormSubmissionInternalError("Test");
        }
    }
}
