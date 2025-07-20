package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.dto.FormsDTO;

import java.sql.SQLException;

public interface FormsMethods {
    /**
     * Creates a completely brand new form. An endpoint must be created first before running this
     * function
     *
     * @param formsDTO   should contain everything necessary to perform operations
     * @throws SQLException
     */
    void createNewForm(FormsDTO formsDTO) throws SQLException;

    /**
     * Deletes a form given the endpoint or the id (not decided yet which one)
     *
     * @throws SQLException
     */
    void deleteForm() throws SQLException;
}
