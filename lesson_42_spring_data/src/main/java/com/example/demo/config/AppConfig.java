package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/lesson42");
		driverManagerDataSource.setUsername("postgres");
		driverManagerDataSource.setPassword("");
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		return driverManagerDataSource;
	}

}
