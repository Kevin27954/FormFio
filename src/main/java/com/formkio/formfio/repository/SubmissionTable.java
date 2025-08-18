package com.formkio.formfio.repository;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.exceptions.FormSubmissionError;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.MalformDataError;
import com.formkio.formfio.model.SubmissionsModel;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.SubmissionMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<SubmissionDTO> getFormSubmissions(String endpoint, Map<String, String> params) {
        String stmt = SELECT_SUBMISSION + "WHERE endpoint=(?) ";

        String dir = params.getOrDefault("change", "1");
        if (dir.equalsIgnoreCase("1")) {
            stmt += "AND id>(?) "
                    + "ORDER BY created_at ";
        } else if (dir.equalsIgnoreCase("-1")) {
            stmt += "AND id<=(?) "
                    + "ORDER BY created_at DESC ";
        } else {
            System.out.println("List<SubmissionDTO> getFormSubmissions: dir was not 1 or -1: " + dir);
            throw new MalformDataError("Data is in correct");
        }
        stmt += "LIMIT (?) " + ";";

        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            int id_val = Integer.parseInt(params.getOrDefault("last", "0"));
            int limit = Integer.parseInt(params.getOrDefault("size", "15"));

            pStmt.setString(1, endpoint);
            pStmt.setInt(2, id_val);
//            pStmt.setString(3, dir);
            pStmt.setInt(3, limit + 1);
            ResultSet result = pStmt.executeQuery();

            List<SubmissionDTO> list = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String data = result.getString("data");
                String source = result.getString("source");
                LocalDateTime createdDate = result.getTimestamp("created_at").toLocalDateTime();
                // Since we are only getting submissions from an endpoint, it doesn't make sense to
                // get it from the result when I can just use the params
                SubmissionDTO submissionDTO = new SubmissionDTO(id, data, source, endpoint, createdDate);

                list.add(submissionDTO);
            }

            list.sort(SubmissionDTO::compareTo);
            return list;
        } catch (SQLException e) {
            System.out.println("List<SubmissionDTO> getFormSubmissions():" + e);
            throw new InternalError("Unable to get form submissions");
        }
    }
}
