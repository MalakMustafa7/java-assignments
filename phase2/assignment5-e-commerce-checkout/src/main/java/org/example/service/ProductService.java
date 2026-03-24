package org.example.service;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    void deleteProductById(Long productId);
}
