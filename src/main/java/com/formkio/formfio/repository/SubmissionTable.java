package com.formkio.formfio.repository;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.form_submission_errors.FormSubmissionInternalError;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
public class SubmissionTable implements SubmissionMethods {

    private final DBDriver dbDriver;

    private final String INSERT_SUBMISSION = "INSERT INTO submissions";

    public SubmissionTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public List<String> getFields(String formId) {
        return Arrays.asList("name", "password");
    }

    @Override
    public void createNewFormSubmission(SubmissionDTO submissionDTO) {
        String stmt = INSERT_SUBMISSION + "(data, source, ip_addr, endpoint) VALUES(?,?,?,?);";
        try(PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, submissionDTO.getData());
            pStmt.setString(2, submissionDTO.getSource());
            pStmt.setString(3, submissionDTO.getIp_addr());
            pStmt.setString(4, submissionDTO.getEndpoint());
            pStmt.execute();
        } catch (SQLException e) {
            throw new FormSubmissionInternalError("");
        }
    }
}
