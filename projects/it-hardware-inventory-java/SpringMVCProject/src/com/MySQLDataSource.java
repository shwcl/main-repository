package com.zinnia.springmvc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MySQLDataSource {
	
	DriverManagerDataSource dataSource;
	public String nameString;
	
	//constructor
	public MySQLDataSource() {
		
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql//localhost:3306/asset_db");
		dataSource.setUsername("xxxx");
		dataSource.setPassword("xxxx");
	}
}
