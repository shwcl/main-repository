package com.blueberry.springbootecommerce.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.blueberry.springbootecommerce.entity.Product;
import com.blueberry.springbootecommerce.service.ProductService;

// Missing/misplaced "@Service" annotation  caused spring boot not not find ProductService bean

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductRestController {
	
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") int productId) {
		return productService.getProduct(productId);
			
	}
	
	
	@GetMapping("/products/category/{id}")
	public List<Product> getProductsByCategory(@PathVariable("id") int categoryId) {
		return productService.getProductsByCategoryId(categoryId);
			
	}
	
	
	
	
	
	
}
