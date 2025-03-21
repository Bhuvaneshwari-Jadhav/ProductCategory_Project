package com.productapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.productapp.model.Category;
import com.productapp.model.Product;
import com.productapp.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(int page, int size) {
		return categoryRepository.findAll(PageRequest.of(page, size));
	}

	public Category isAddCategory(Category category) {
		return categoryRepository.save(category);
	}

	public List<Category> getAllCategories() {
		List<Category> list = categoryRepository.findAll();
		return list;
	}

	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}

	public String updateCategory(int id, Category category) {
		Optional<Category> o = categoryRepository.findById(id);
		if (o.isPresent()) {
			Category newCategory = o.get();
			newCategory.setName(category.getName());
			newCategory.setProduct(category.getProduct());
			categoryRepository.save(newCategory);
			return "Category updated Successfully....";
		} else {
			return "Category Not updated Successfully....";
		}
	}

	public String deleteCategoryById(int id) {
		Optional<Category> o = categoryRepository.findById(id);
		if (o.isEmpty()) {
			return "Category not present";
		} else {
			categoryRepository.deleteById(id);
			return "Category Deleted Successfully....";
		}
	}

}
