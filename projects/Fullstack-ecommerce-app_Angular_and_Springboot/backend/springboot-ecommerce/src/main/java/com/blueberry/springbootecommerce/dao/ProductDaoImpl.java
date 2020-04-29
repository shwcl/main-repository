package com.blueberry.springbootecommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.blueberry.springbootecommerce.entity.Product;
import com.blueberry.springbootecommerce.entity.ProductRowMapper;

@Repository("ProductDao")
public class ProductDaoImpl implements ProductDao {
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbctemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
		
		
	// method to get a product
	public Product getProduct(int productId) {
		
		Product product = null;
		
		try {
			
			String sql = "SELECT product.id, sku, name, description, unit_price, image_url, active, units_in_stock, category_id, category_name \r\n" + 
					"FROM product\r\n" + 
					"JOIN product_category\r\n" + 
					"ON product.category_id = product_category.id\r\n" + 
					"WHERE product.id = ?";
		
			return this.jdbcTemplate.queryForObject(sql, new Object[] {productId}, new ProductRowMapper());
		
		
		}  catch(Exception e) {
			System.out.println(e);
			product = null;
		}
		  return product;
	}
	
		
	// method to get all products
	public List<Product> getProducts() {
		
		String sql = "SELECT product.id, sku, name, description, unit_price, image_url, active, units_in_stock, category_id, category_name \r\n" + 
				"FROM product\r\n" + 
				"JOIN product_category\r\n" + 
				"ON product.category_id = product_category.id";

		return this.jdbcTemplate.query(sql, new ProductRowMapper());

	}
	
	
	// method to get products by Product category id
	public List<Product> getProductsByCategoryId(int categoryId) {
		
		String sql = "SELECT product.id, sku, name, description, unit_price, image_url, active, units_in_stock, category_id, category_name \r\n" + 
				"FROM product\r\n" + 
				"JOIN product_category\r\n" + 
				"ON product.category_id = product_category.id WHERE product.category_id = ? ";

		return this.jdbcTemplate.query(sql, new Object[] {categoryId}, new ProductRowMapper());

	}
	
	
	// method to get products by Product id
	public List<Product> getProductsByProductId(int productId) {
		
		String sql = "SELECT product.id, sku, name, description, unit_price, image_url, active, units_in_stock, category_id, category_name \r\n" + 
				"FROM product\r\n" + 
				"JOIN product_category\r\n" + 
				"ON product.category_id = product_category.id WHERE product.id = ? ";

		return this.jdbcTemplate.query(sql, new Object[] {productId}, new ProductRowMapper());

	}
	
	
	
	
	

}
