package com.formkio.formfio.repository;

import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.UsersMethods;
import com.stripe.model.Customer;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class UsersTable implements UsersMethods {

    DBDriver dbDriver;
    private final String INSERT_USER = "INSERT INTO users";
    private final String SELECT_USER = "SELECT * FROM users";

    private final String SELECT_FORMS = "SELECT * FROM forms";

    public UsersTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createNewUser(UsersModel usersModel, Customer customer) {
        String stmt = INSERT_USER + "(stripe_id, email, account_plan, is_referred, free_trial) VALUES (?,?,?,?,?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, customer.getId());
            pStmt.setString(2, usersModel.getEmail());
            pStmt.setInt(3, usersModel.getAccountPlan());
            pStmt.setInt(4, usersModel.getIsReferred());
            pStmt.setTimestamp(5, Timestamp.valueOf(usersModel.getFreeTrial()));
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println("void createNewUser" + e);
            throw new InternalError("Unable to create user. Try again later");
        }
    }

    @Override
    public UsersModel getUserByEndpoint(String endpoint) {
        String stmt = SELECT_USER + " JOIN forms ON users.email=forms.email WHERE forms.endpoint = (?);";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, endpoint);
            ResultSet result = pStmt.executeQuery();

            if (!result.next()) {
                // TODO give this an actual Error class (Can't find user or something)
                throw new InternalError("Unable to find user");
            }

            UsersModel user = new UsersModel();
            user.setEmail(result.getString("email"));
            user.setIsReferred(result.getInt("is_referred"));
            user.setAccountPlan(result.getInt("account_plan"));
            user.setFreeTrial(result.getTimestamp("created_at").toLocalDateTime());

            return user;
        } catch (SQLException e) {
            // TODO Same as the todo above
            System.out.println("UsersModel getUserByEndpoint(String endpoint): " + e);
            throw new InternalError("Unable to find user");
        }
    }
}
