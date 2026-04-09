package org.example.strategy.discount;

import lombok.AllArgsConstructor;
import org.example.model.Cart;

import java.math.BigDecimal;
@AllArgsConstructor
public class PercentageDiscount implements DiscountStrategy {
    private final BigDecimal discountPercentage;
    @Override
    public BigDecimal calculateDiscountAmount(Cart cart) {
       return cart.getCartItems().values().stream()
               .map(item->item.getProduct().getPrice()
                       .multiply(BigDecimal.valueOf(item.getQuantity())).multiply(discountPercentage))
               .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
