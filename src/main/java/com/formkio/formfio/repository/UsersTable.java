package com.formkio.formfio.repository;

import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.UsersMethods;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersTable implements UsersMethods {
    DBDriver dbDriver;

    public UsersTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewUser() {
        try (PreparedStatement pStmt = dbDriver.prepareStatement("")) {
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
