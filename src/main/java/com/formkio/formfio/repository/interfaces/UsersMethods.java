package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.model.UsersModel;

import java.sql.SQLException;

public interface UsersMethods {

    void createNewUser(UsersModel usersModel) throws SQLException;
}
