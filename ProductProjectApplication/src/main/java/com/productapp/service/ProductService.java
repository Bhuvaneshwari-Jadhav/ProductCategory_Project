package com.productapp.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.productapp.model.Product;
import com.productapp.repository.ProductRepository;

@Service("productService")
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

    public Page<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
    
    public Product isAddProduct(Product product) {
        return productRepository.save(product);
    }
    
    public List<Product> getAllProducts() {
    	List<Product> list=productRepository.findAll();
        return list;
    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }
    public String updateProduct(int id, Product product) {
		Optional<Product> o = productRepository.findById(id);
		if (o.isPresent()) {
			Product newProduct = o.get();
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());;
			newProduct.setCategory(product.getCategory());
			productRepository.save(newProduct);
			return "Product updated Successfully....";
		} 
		else {
			return "Product Not updated Successfully....";
		}
    }
    public String deleteProductById(int id) 
	{
		Optional<Product> o=productRepository.findById(id);
		if(o.isEmpty())
		{
			return "Product not present";
	    }
		else
		{
			productRepository.deleteById(id);
			return "Product Deleted Successfully....";
		}
	}
}
