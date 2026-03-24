package org.example.Validation.impl;

import org.example.Validation.ProductValidator;
import org.example.model.Product;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public class ProductValidatorImpl implements ProductValidator {
    @Override
    public void validate(Product product) {
        validateProduct(product);
        validateName(product.getName());
        validatePrice(product.getPrice());
        validateQuantity(product.getQuantity());
    }

    private void validateName(String name) {
        if(name == null||name.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.NAME_IS_REQUIRED);
        }
    }


    private void validatePrice(BigDecimal price) {
        if(price == null||price.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException(ErrorMessages.POSITIVE_PRICE);
        }

    }

    private void validateQuantity(int quantity) {
       if(quantity<0){
           throw new IllegalArgumentException(ErrorMessages.QUANTITY_MUST_BE_POSITIVE);
       }
    }
    private void validateProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException(ErrorMessages.CANNOT_BE_NULL);
        }
    }


}
