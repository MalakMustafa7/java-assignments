package org.example.factroy;

import org.example.enums.DiscountType;
import org.example.strategy.discount.DiscountStrategy;

public interface DiscountFactory {
    DiscountStrategy createDiscount(DiscountType discountType);
}
