package com.productapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.productapp.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
}
