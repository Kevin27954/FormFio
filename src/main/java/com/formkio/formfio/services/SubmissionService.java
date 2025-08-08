package com.formkio.formfio.services;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.FormSubmissionError;
import com.formkio.formfio.exceptions.LimitPassedError;
import com.formkio.formfio.model.SubmissionsModel;
import com.formkio.formfio.repository.SubmissionTable;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SubmissionService {

    private DBDriver dbDriver;
    private SubmissionTable submissionTable;

    private final JedisLimiterService jedisLimiterService;
    private final EmailService emailService;
    private final JSONService jsonService;

    private final String INSERT_SUBMISSION = "INSERT INTO submissions";

    public SubmissionService(DBDriver dbDriver,SubmissionTable submissionTable, JedisLimiterService jedisLimiterService, EmailService emailService, JSONService jsonService) {
        this.dbDriver = dbDriver;
        this.submissionTable = submissionTable;
        this.jedisLimiterService = jedisLimiterService;
        this.emailService = emailService;
        this.jsonService = jsonService;
    }

    Map<String, String> extractFormFields(Map<String, String> submission) {
        return null;
    }

    public void save(SubmissionsModel submissionsModel) throws FormSubmissionError {
        // Extra logic here if needed
        submissionTable.createNewFormSubmission(submissionsModel);
    }

    public void processSubmission(String endpoint, Map<String,String> submission, String ipAddr) {
        if (!jedisLimiterService.isAllowed(endpoint)) {
            throw new LimitPassedError("You've hit Your Maximum Limit.");
        }

        // TODO impleemnt this function in the future
        this.emailService.sendEmail();

        String submissionJson = jsonService.jsonStringify(submission);
        SubmissionsModel submissionDTO = new SubmissionsModel(submissionJson, "web", ipAddr, endpoint);
        save(submissionDTO);
    }


    public List<SubmissionDTO> getSubmissions(String endpoint, Map<String, String> params) {
        int page;
        String sortBy;

        page = Integer.parseInt(params.getOrDefault("page", "0"));

        return this.submissionTable.getFormSubmissions(endpoint);
    }
}
