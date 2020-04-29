package com.blueberry.springbootecommerce.service;

import java.util.List;
import com.blueberry.springbootecommerce.entity.Product;


public interface ProductService {
	
	public List<Product> getProducts();
	public Product getProduct(int productId);
	public List<Product> getProductsByCategoryId(int categoryId);
	public List<Product> getProductsByProductId(int productId);

}
