package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.model.UsersModel;

import java.sql.SQLException;
import java.util.List;

public interface FormsMethods {
    /**
     * Creates a completely brand new form. An endpoint must be created first before running this
     * function
     * @param formsDTO should contain everything necessary to perform operations
     * @throws SQLException
     */
    void createNewForm(FormsDTO formsDTO) throws SQLException;

    /**
     * Grabs all the form information associated with the User.
     * @throws SQLException
     */
    List<FormsDTO> getForms(UsersModel usersModel) throws SQLException;

    /**
     * Deletes a form given the endpoint or the id (not decided yet which one)
     * @throws SQLException
     */
    void deleteForm() throws SQLException;
}
