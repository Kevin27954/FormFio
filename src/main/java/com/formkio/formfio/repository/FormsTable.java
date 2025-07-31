package com.formkio.formfio.repository;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.FormsMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FormsTable implements FormsMethods {

    private DBDriver dbDriver;

    private final String INSERT_FORMS = "INSERT INTO forms ";
    private final String SELECT_FORMS = "SELECT * FROM forms ";

    public FormsTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewForm(FormsDTO formsDTO) throws InternalError {
        String stmt = INSERT_FORMS + "(email, form_name, description, endpoint) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, formsDTO.getUsersModel().getEmail());
            pStmt.setString(2, formsDTO.getFormName());
            pStmt.setString(3, formsDTO.getDescription());
            pStmt.setString(4, formsDTO.getEndpoint());
            pStmt.execute();
        } catch (SQLException e) {
            // TODO consider better error
            System.out.println("void createNewForm:" + e);
            throw new InternalError();
        }
    }

    @Override
    public List<FormsDTO> getForms(UsersModel usersModel) throws SQLException {
        String stmt = SELECT_FORMS + "WHERE email=?;";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, usersModel.getEmail());
            ResultSet result = pStmt.executeQuery();

            List<FormsDTO> list = new ArrayList<>();
            while(result.next()) {
                FormsDTO formsDTO = new FormsDTO();
                formsDTO.setEndpoint(result.getString("endpoint"));
                formsDTO.setFormName(result.getString("form_name"));
                formsDTO.setDescription(result.getString("description"));

                list.add(formsDTO);
            }

            return list;
        } catch (SQLException e) {
            // TODO consider better error
            System.out.println("List<FormsDTO> getForms formsTable: " + e);
            throw new InternalError();
        }
    }

    @Override
    public void deleteForm() throws SQLException {

    }
}
