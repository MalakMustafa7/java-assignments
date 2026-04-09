package org.example.Validation.impl;

import org.example.Validation.CartValidator;
import org.example.exception.NotFoundException;
import org.example.model.Cart;
import org.example.utility.ErrorMessages;

public class CartValidatorImpl implements CartValidator {
    @Override
    public void validateCartNotEmpty(Cart cart) {
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.EMPTY_CART);
        }
    }

    @Override
    public void validateCartItemExists(Cart cart, Long productId) {
        if (!cart.getCartItems().containsKey(productId)) {
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }

    }

    @Override
    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(ErrorMessages.QUANTITY_MUST_BE_POSITIVE);
        }

    }
}
