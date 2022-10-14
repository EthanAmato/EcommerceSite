package com.tts.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<String> findDistinctCategories() {
		return productRepository.findDistinctCategories();
	}

	public List<Product> findByBrandAndOrCategory(String brand, String category) {
		if (category == null && brand == null) {
			return productRepository.findAll();
		} else if (category == null) {
			return productRepository.findByBrand(brand);
		} else if (brand == null) {
			return productRepository.findByCategory(category);
		} else {
			return productRepository.findByBrandAndCategory(brand, category);
		}
	}

	public List<String> findDistinctBrands() {
		return productRepository.findDistinctBrands();
	}

	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

	public void save(@Valid Product product) {
		productRepository.save(product);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
