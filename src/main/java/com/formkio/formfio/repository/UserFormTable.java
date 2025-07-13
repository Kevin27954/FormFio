package com.formkio.formfio.repository;

import java.sql.SQLException;
import java.util.List;

public interface UserFormTable {
    List<String> getFields(String formId);

    void createTable() throws SQLException;
}
