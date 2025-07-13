package com.formkio.formfio.repository;

import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFormRepo implements UserFormTable {

    DBDriver dbDriver;

    public UserFormRepo(DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public void createTable() {
        try (Statement stmt = this.dbDriver.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXIST submissions ("
                            + "id INTEGER PRIMARY KEY"
                            + "data TEXT" // Form data in JSON
                            + "source TEXT NOT NULL" // The place received from (web, discord, etc)
                            + "ip_addr TEXT" // The id_addr
                            + "endpoint TEXT NOT NULL REFERENCES endpoints(endpoint)" // unique key to easily grab things
                            + "created_date DATE NOT NULL" // when it was  created
                            + ");"
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getFields(String formId) {
        return Arrays.asList("name", "password");
    }
}
