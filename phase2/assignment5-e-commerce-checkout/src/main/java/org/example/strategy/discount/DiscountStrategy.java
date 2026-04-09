package org.example.strategy.discount;

import org.example.model.Cart;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculateDiscountAmount(Cart cart);
}
