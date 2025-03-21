package com.productapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productapp.model.Product;
import com.productapp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
    private ProductService productService;
	
	 @GetMapping
	 public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     return productService.getAllProducts(page, size);
	 }
	 @PostMapping
	 public Product isAddProduct(@RequestBody Product product) {
	     return productService.isAddProduct(product);
	 }
	 @GetMapping("/{id}")
	 public ResponseEntity<Product> getProductById(@PathVariable int id) {
	      Optional<Product> product = productService.getProductById(id);
	      return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	 }
	 @DeleteMapping("/{id}")
	 public String deleteProductById(@PathVariable("id") Integer id)
	 {
		  String s = productService.deleteProductById(id);
		  return s;
	 }
	 @PutMapping("/{id}")
	 public String updateProduct(@RequestBody Product product,@PathVariable("id") Integer id) 
	 {
		  String s=productService.updateProduct(id, product);
		  return s;
	  }
}
