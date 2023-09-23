package com.example.laba4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {


    @Bean
    public static DataSource source() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/letters");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin2233");

        return dataSource;
    }
}
