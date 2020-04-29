package com.blueberry.springbootecommerce.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueberry.springbootecommerce.dao.ProductDao;
import com.blueberry.springbootecommerce.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	

	@Autowired
	ProductDao productDao;
	
	
	public Product getProduct(int productId) {
		return productDao.getProduct(productId);
	}
	
	
	public List<Product> getProducts() {
		return productDao.getProducts();
	}
	
	public List<Product> getProductsByCategoryId(int categoryId) {
		return productDao.getProductsByCategoryId(categoryId);
	}
	
	public List<Product> getProductsByProductId(int productId) {
		return productDao.getProductsByProductId(productId);
	}
	
}
