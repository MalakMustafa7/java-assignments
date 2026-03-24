package org.example.Validation;

import org.example.model.Product;

import java.math.BigDecimal;

public interface ProductValidator {
    void validate(Product product);
}
