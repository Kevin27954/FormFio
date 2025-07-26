package com.formkio.formfio.repository;

import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.NotUniqueUUIDError;
import com.formkio.formfio.repository.drivers.DBDriver;
import com.formkio.formfio.repository.interfaces.EndpointsMethods;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EndpointsTable implements EndpointsMethods {
    DBDriver dbDriver;

    private final String INSERT_ENDPOINT = "INSERT INTO endpoints ";
    private final String SELECT_ENDPOINT = "SELECT * FROM endpoints ";

    public EndpointsTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }


    @Override
    public void createNewEndpoint(String endpoint) throws NotUniqueUUIDError {
        String stmt = INSERT_ENDPOINT + "(endpoint) VALUES (?);";
        try (PreparedStatement preparedStatement = dbDriver.prepareStatement(stmt)) {
            preparedStatement.setString(1, endpoint);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("String createNewEndpoint() insert: " + e);
            throw new NotUniqueUUIDError("uuid " + endpoint + " was not unique.");
        }
    }

    @Override
    public void deleteEndpoint(String endpoint) throws SQLException {

    }

    @Override
    public boolean getEndpoint(String endpoint) throws InternalError {
        String stmt = SELECT_ENDPOINT + "WHERE endpoint=?;";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, endpoint);
            ResultSet result = pStmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            // TODO consider better error
            System.out.println("boolean getEndpoint(): " + e);
            throw new InternalError();
        }
    }
}
