package com.zinnia.springmvc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MyJdbcTemplate {
	
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource dataSource) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	


}
