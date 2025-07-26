package com.formkio.formfio.repository;

import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.UsersMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class UsersTable implements UsersMethods {

    DBDriver dbDriver;
    private final String INSERT_USER = "INSERT INTO users";

    public UsersTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewUser(UsersModel usersModel) {
        String stmt = INSERT_USER + "(email, account_plan, is_referred, free_trial) VALUES (?,?,?,?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, usersModel.getEmail());
            pStmt.setInt(2, usersModel.getAccountPlan());
            pStmt.setInt(3, usersModel.getIsReferred());
            pStmt.setInt(4, usersModel.getFreeTrail());
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println("void createNewUser" + e);
            throw new InternalError("Unable to create user. Try again later");
        }
    }
}
