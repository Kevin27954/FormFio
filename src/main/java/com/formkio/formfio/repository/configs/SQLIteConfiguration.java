package com.formkio.formfio.repository.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Profile("dev")
@Configuration
public class SQLIteConfiguration {

    @Bean
    public Connection initConnection(@Value("${spring.datasource.url}") String uri) throws SQLException {
        System.out.println("I'm connected to SQLite");
//        return DriverManager.getConnection("jdbc:sqlite:database/sample.db");
        return DriverManager.getConnection(uri);
    }
}
