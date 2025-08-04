package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.model.UsersModel;

import java.sql.SQLException;

public interface UsersMethods {

    /**
     * Creates a new user. Only the email is required initially. All other fields are optional
     * or can be filled in at a later time.
     * This requires the user be created via Supabase first.
     * @param usersModel
     * @throws SQLException
     */
    void createNewUser(UsersModel usersModel) throws SQLException;
}
