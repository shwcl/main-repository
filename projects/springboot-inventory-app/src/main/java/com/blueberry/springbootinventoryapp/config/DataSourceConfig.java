package com.blueberry.springbootinventoryapp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableWebMvc   -- not necesary in Spring boot .. Spring boots finds Spring-mvc in class path ..  affects css rendering
@ComponentScan("com.blueberry.springbootinventoryapp")
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		//Using MySQL database
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/asset_db");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
	}
	

	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

}
