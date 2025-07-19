package com.formkio.formfio.repository;

import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.NotUniqueUUIDError;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class EndpointsTable implements EndpointsMethods {
    DBDriver dbDriver;

    private final String INSERT_ENDPOINT = "INSERT INTO endpoints ";
    private final String SELECT_ENDPOINT = "SELECT FROM endpoints ";

    public EndpointsTable(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }


    @Override
    public String createNewEndpoint() throws NotUniqueUUIDError {
        // There will be a time when I need to write a quick function
        // to check if it is unique and retry until it is.
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        try {
            while (getEndpoint(uuidString)) {
                uuid = UUID.randomUUID();
                uuidString = uuid.toString();
            }
        } catch (InternalError e) {
            System.out.println("String createNewEndpoint() uuid: " + e);
            throw new NotUniqueUUIDError("uuid " + uuidString + " was not unique.");
        }

        String stmt = INSERT_ENDPOINT + "(endpoint) VALUES (?);";
        try (PreparedStatement preparedStatement = dbDriver.prepareStatement(stmt)) {
            preparedStatement.setString(1, uuidString);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("String createNewEndpoint() insert: " + e);
            throw new NotUniqueUUIDError("uuid " + uuidString + " was not unique.");
        }

        return uuidString;
    }

    @Override
    public void deleteEndpoint(String endpoint) throws SQLException {

    }

    @Override
    public boolean getEndpoint(String endpoint) throws InternalError {
        String stmt = SELECT_ENDPOINT + "WHERE endpoint=(?)";
        try (PreparedStatement pStmt = dbDriver.prepareStatement(stmt)) {
            pStmt.setString(1, endpoint);
            ResultSet result = pStmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("boolean getEndpoint(): " + e);
            throw new InternalError("Unable to fetch endpoint from table");
        }
    }
}
