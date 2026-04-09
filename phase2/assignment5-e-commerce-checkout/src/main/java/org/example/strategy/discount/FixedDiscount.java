package org.example.strategy.discount;

import lombok.AllArgsConstructor;
import org.example.model.Cart;

import java.math.BigDecimal;
@AllArgsConstructor
public class FixedDiscount implements DiscountStrategy {
    private  BigDecimal fixedAmount;

    @Override
    public BigDecimal calculateDiscountAmount(Cart cart) {
        BigDecimal subtotal = cart.getCartItems().values().stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

         return fixedAmount.min(subtotal);
    }
}
