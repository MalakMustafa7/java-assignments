package org.example;

import org.example.Validation.ProductValidator;
import org.example.Validation.impl.ProductValidatorImpl;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.service.ProductService;
import org.example.service.impl.ProductServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ProductValidator validator = new ProductValidatorImpl();
        ProductRepository repo = new ProductRepositoryImpl();
        ProductService service = new ProductServiceImpl(repo,validator);

        Product p1 = new Product(1L,"pen", BigDecimal.valueOf(100),20);
        Product p2 = new Product(2L,"Notebook", BigDecimal.valueOf(200),10);

        service.addProduct(p1);
        service.addProduct(p2);
        System.out.println(service.getAllProducts());
        service.getProductById(1L);
        service.deleteProductById(1L);
        System.out.println(service.getAllProducts());


    }
}