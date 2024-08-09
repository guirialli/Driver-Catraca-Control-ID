package com.atlantic.turnstiles.config.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConfigureSqlite {
	
	@Value("${spring.datasource.driverClassName}")
	String className;
	
	@Value("${spring.datasource.url}")
	String url;
	
	@Value("${spring.datasource.user}")
	String user;
	
	@Value("${spring.datasource.password}")
	String password;
	
	

	@Bean
    DataSource dataSource() {
	    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(className);
	    dataSource.setUrl(url);
	    dataSource.setUsername(user);
	    dataSource.setPassword(password);
	    return dataSource;
    }
}
