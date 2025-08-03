package com.formkio.formfio.repository.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class SupabaseConfiguration {

    @Bean
    public Connection initSupabaseDriver(@Value("${spring.prod.sql}") String uri) throws SQLException {
        return DriverManager.getConnection(uri);
    }

}
