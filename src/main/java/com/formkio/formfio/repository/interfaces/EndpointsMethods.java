package com.formkio.formfio.repository.interfaces;

import java.sql.SQLException;

public interface EndpointsMethods {
    /**
     *  This should generate a random new id and it should be associated with the
     *  form that it is created for. Once created, it can not be modified.
     * @param endpoint a uuid v4 string
     * @throws SQLException
     */
    void createNewEndpoint(String endpoint) throws SQLException;

    /**
     * Given an endpoint that is associated with an Form, it would be deleted from the
     * database.
     * @param endpoint the endpoint associated with the form you want to delete.
     * @throws SQLException
     */
    void deleteEndpoint(String endpoint) throws SQLException;

    /**
     * It checks the database to see if the endpoint you want exist.
     * @param endpoint the endpoint that we are looking for.
     * @return true if exist , false if doesn't exist
     * @throws SQLException
     */
    boolean getEndpoint(String endpoint) throws SQLException;
}
