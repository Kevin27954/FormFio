package com.formkio.formfio.repository.drivers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;

@Profile("prod")
@Component
public class PostgreSQLDriver extends DBDriver {

    public PostgreSQLDriver(Connection postgres) {
        this.conn = postgres;
    }

    @Override
    public PreparedStatement prepareStatement(String sqlStatement) throws SQLException {
        return conn.prepareStatement(sqlStatement);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return conn.createStatement();
    }

    @Override
    public void beginTransaction() throws SQLException {
        conn.setAutoCommit(false);
    }

    @Override
    public void rollback() throws SQLException {
        conn.rollback();
    }

    @Override
    public void commit() throws SQLException {
        conn.commit();
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

}
