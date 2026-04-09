package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    void deleteProductById(Long productId);
    void checkInStock(Long productId, int requiredQty);
    void decreaseQuantity(Long productId, int newQty);
}
