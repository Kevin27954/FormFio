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
    public List<String> getFields(String formId) {
        return Arrays.asList("name", "password");
    }
}
