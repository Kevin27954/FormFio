package com.formkio.formfio.repository.drivers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface DBDriver {
    PreparedStatement prepareStatement(String sqlStatement) throws SQLException;
    Statement createStatement() throws SQLException;

    void close() throws SQLException;
}
