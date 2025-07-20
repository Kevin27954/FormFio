package com.formkio.formfio.repository;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.FormsMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class FormsTable implements FormsMethods {

    DBDriver dbDriver;

    final String INSERT_FORMS = "INSERT INTO forms ";

    public FormsTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewForm(FormsDTO formsDTO) throws InternalError {
        String stmt = INSERT_FORMS + "(user, form_name, description, endpoint) VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = dbDriver.prepareStatement(stmt)) {
            preparedStatement.setString(1, formsDTO.getUsersModel().getEmail());
            preparedStatement.setString(2, formsDTO.getName());
            preparedStatement.setString(3, formsDTO.getDescription());
            preparedStatement.setString(4, formsDTO.getEndpoint());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
            throw new InternalError("Unable to create form. Internal Server Error. Try again later.");
        }
    }

    @Override
    public void deleteForm() throws SQLException {

    }
}
