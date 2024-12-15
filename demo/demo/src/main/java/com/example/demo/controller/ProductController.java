package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // GET all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // POST: Add a new product
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // PUT: Update product
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setStock(productDetails.getStock());
        return productRepository.save(product);
    }

    // DELETE: Remove a product
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Product deleted with id: " + id;
    }

    // GET all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // POST: Add a new category
    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
