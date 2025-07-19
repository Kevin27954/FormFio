package com.formkio.formfio.repository;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.model.UsersModel;

import java.sql.SQLException;

public interface FormsMethods {
    /**
     * Creates a completely brand new form. An endpoint must be created first before running this
     * function
     *
     * @param formsDTO   should contain everything necessary to perform operations
     * @param usersModel contains information about the login user
     * @throws SQLException
     */
    void createNewForm(UsersModel usersModel, FormsDTO formsDTO) throws SQLException;

    /**
     * Deletes a form given the endpoint or the id (not decided yet which one)
     *
     * @throws SQLException
     */
    void deleteForm() throws SQLException;
}
