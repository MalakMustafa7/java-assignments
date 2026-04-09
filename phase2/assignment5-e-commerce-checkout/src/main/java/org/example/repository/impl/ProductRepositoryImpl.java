package org.example.repository.impl;

import org.example.model.Product;
import org.example.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long,Product> products = new HashMap<>();
    @Override
    public void save(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products.values().stream().toList();
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public void deleteById(Long productId) {
        products.remove(productId);
    }
}
