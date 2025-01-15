package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long productIdCounter = 1L;

    // Create Product
    public Product createProduct(String name, double price) {
        Product product = new Product(productIdCounter++, name, price);
        products.add(product);
        return product;
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return products;
    }

    // Get Product by ID
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // Update Product
    public Product updateProduct(Long id, String name, double price) {
        Optional<Product> productOptional = getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(name);
            product.setPrice(price);
            return product;
        }
        return null;
    }

    // Delete Product
    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
