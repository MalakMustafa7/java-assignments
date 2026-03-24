package org.example.repository;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    List<Product> getAllProducts();
    Optional<Product> findById(Long productId);
    void deleteById(Long productId);
}
