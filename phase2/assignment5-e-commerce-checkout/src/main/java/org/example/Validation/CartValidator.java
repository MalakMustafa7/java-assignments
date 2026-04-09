package org.example.Validation;

import org.example.model.Cart;

public interface CartValidator {
    void validateCartNotEmpty(Cart cart);
    void validateCartItemExists(Cart cart, Long productId);
    void validateQuantity(int quantity);
}
