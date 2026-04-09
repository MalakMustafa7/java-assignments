package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.Validation.ProductValidator;
import org.example.exception.NotFoundException;
import org.example.exception.OutOfStockException;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.example.utility.ErrorMessages;

import java.util.List;
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductValidator productValidator){
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }
    @Override
    public void addProduct(Product product) {
        productValidator.validate(product);
        productRepository.save(product);
        log.info("{} added successfully",product.getName());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND));
    }

    @Override
    public void deleteProductById(Long productId) {
       Product product= getProductById(productId);
        productRepository.deleteById(productId);
        log.info("{} removed successfully",product.getName());
    }

    @Override
    public void checkInStock(Long productId, int requiredQty) {
        Product product = getProductById(productId);
       if(product.getQuantity()<requiredQty){
           throw new OutOfStockException(ErrorMessages.OUT_OF_STOCK);
       }
    }

    @Override
    public void decreaseQuantity(Long productId, int newQty) {
        Product product = getProductById(productId);
        if(product.getQuantity()<newQty){
            throw new OutOfStockException(ErrorMessages.OUT_OF_STOCK);
        }
        int quantity = product.getQuantity()-newQty;
        product.setQuantity(quantity);
        productRepository.save(product);
    }
}
