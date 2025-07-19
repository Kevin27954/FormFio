package com.formkio.formfio.repository.drivers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBDriver {

    protected boolean isTransaction = false;
    protected Connection conn;

    public abstract PreparedStatement prepareStatement(String sqlStatement) throws SQLException;

    public abstract Statement createStatement() throws SQLException;

    public abstract void beginTransaction() throws SQLException;

    public abstract void rollback() throws SQLException;

    public abstract void commit() throws SQLException;

    public abstract void close() throws SQLException;
}
