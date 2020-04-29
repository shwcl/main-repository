package com.blueberry.springbootecommerce.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ProductRowMapper implements RowMapper<Product> {
	
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		product.setId(rs.getInt(1));
		product.setSku(rs.getString(2));
		product.setName(rs.getString(3));
		product.setDescription(rs.getString(4));
		product.setUnitPrice(rs.getDouble(5));
		product.setImageUrl(rs.getString(6));
		product.setActive(rs.getBoolean(7));
		product.setUnitsInStock(rs.getInt(8));
		product.setCategory_id(rs.getInt(9));
		
		return product;

	}
	

	

}
