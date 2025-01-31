package com.example.productservice.controller;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestParam String name, @RequestParam double price) {
        return productService.createProduct(name, price);
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResourceNotFoundException("Product not found with ID " + id);
        }
    }

    // Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam double price) {
        Product updatedProduct = productService.updateProduct(id, name, price);
        if (updatedProduct == null) {
            throw new ResourceNotFoundException("Product not found with ID " + id);
        }
        return updatedProduct;
    }

    // Delete Product
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            throw new ResourceNotFoundException("Product not found with ID " + id);
        }
    }

    // Custom Exception Handler for ResourceNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }
}
