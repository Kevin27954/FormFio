package com.formkio.formfio.repository.drivers;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;

@Profile("dev")
@Component
public class SQLiteDriver implements DBDriver {
    Connection conn;

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
    @PreDestroy
    public void close() throws SQLException {
        this.conn.close();
    }
}
