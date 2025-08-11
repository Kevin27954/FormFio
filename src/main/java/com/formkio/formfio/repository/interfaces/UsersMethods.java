package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.model.UsersModel;
import com.stripe.model.Customer;

import java.sql.SQLException;

public interface UsersMethods {

    /**
     * Creates a new user. Only the email is required initially. All other fields are optional
     * or can be filled in at a later time.
     * This requires the user be created via Supabase first.
     * @param usersModel, customer (a Stripe object) must be added
     * @throws SQLException
     */
    void createNewUser(UsersModel usersModel, Customer customer) throws SQLException;

    /**
     * Grabs the users information given a endpoint associated to the form. This is specifically made for
     * grabbing user information in the JedisLimiterService.
     * @param endpoint
     * @return UsersModel containing the information about the user
     * @throws SQLException
     */
    UsersModel getUserByEndpoint(String endpoint) throws SQLException;
}
