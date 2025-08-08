package com.formkio.formfio.repository;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.FormSubmissionError;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.model.SubmissionsModel;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.SubmissionMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubmissionTable implements SubmissionMethods {

    private final DBDriver dbDriver;

    private final String INSERT_SUBMISSION = "INSERT INTO submissions ";
    private final String SELECT_SUBMISSION = "SELECT * FROM submissions ";

    public SubmissionTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewFormSubmission(SubmissionsModel submissionsModel) {
        String stmt = INSERT_SUBMISSION + "(data, source, ip_addr, endpoint) VALUES(?,?,?,?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, submissionsModel.getData());
            pStmt.setString(2, submissionsModel.getSource());
            pStmt.setString(3, submissionsModel.getIpAddr());
            pStmt.setString(4, submissionsModel.getEndpoint());
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println("void createNewFormSubmission:" + e);
            throw new FormSubmissionError("Unable to submit form response. Please try again later.");
        }
    }

    @Override
    public List<SubmissionDTO> getFormSubmissions(String endpoint) {
        String stmt = SELECT_SUBMISSION + "WHERE endpoint=(?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, endpoint);
            ResultSet result = pStmt.executeQuery();

            List<SubmissionDTO> list = new ArrayList<>();
            while(result.next()) {
                String data = result.getString("data");
                String source = result.getString("source");
                LocalDateTime createdDate = result.getTimestamp("created_at").toLocalDateTime();
                // Since we are only getting submissions from an endpoint, it doesn't make sense to
                // get it from the result when I can just use the params
                SubmissionDTO submissionDTO = new SubmissionDTO(data, source, endpoint, createdDate);

                list.add(submissionDTO);
            }

            return list;
        } catch (SQLException e) {
            System.out.println("List<SubmissionDTO> getFormSubmissions():" + e);
            throw new InternalError("Unable to get form submissions");
        }
    }
}
