package com.formkio.formfio.repository.drivers;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SQLDriver extends DBDriver {

    public SQLDriver(Connection postgres) {
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
        conn.setAutoCommit(true);
    }

    @PreDestroy
    @Override
    public void close() throws SQLException {
        conn.close();
    }

}
