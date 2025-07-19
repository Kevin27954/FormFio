package com.formkio.formfio.repository.drivers;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;

@Profile("dev")
@Component
public class SQLiteDriver extends DBDriver {

    public SQLiteDriver() throws SQLException {
        String SQLITE_DB_CONN = "jdbc:sqlite:database/sample.db";
        this.conn = DriverManager.getConnection(SQLITE_DB_CONN);
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
    public void rollback() throws SQLException {
        conn.rollback();
    }

    @Override
    public void beginTransaction() throws SQLException {
        conn.setAutoCommit(false);
        this.isTransaction = true;
    }

    @Override
    public void commit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    @Override
    @PreDestroy
    public void close() throws SQLException {
        this.conn.close();
    }
}
