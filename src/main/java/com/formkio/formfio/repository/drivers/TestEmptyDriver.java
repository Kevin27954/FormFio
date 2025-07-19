package com.formkio.formfio.repository.drivers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Profile("notdev")
@Component
public class TestEmptyDriver extends DBDriver {
    @Override
    public void beginTransaction(){

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public void commit() throws SQLException {

    }

    public TestEmptyDriver() {
        System.out.println("testempydriver");
    }

    @Override
    public PreparedStatement prepareStatement(String sqlStatement) throws SQLException {
        return null;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return null;
    }

    @Override
    public void close() throws SQLException {

    }

}
