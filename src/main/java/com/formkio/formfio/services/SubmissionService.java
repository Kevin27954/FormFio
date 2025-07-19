package com.formkio.formfio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.exceptions.form_submission_errors.FormSubmissionInternalError;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubmissionService {

    public SubmissionService() {
    }

    Map<String, String> extractFormFields(Map<String, String> submission) {

        return null;
    }

    void saveForm() {

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
