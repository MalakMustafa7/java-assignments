package org.example.factroy.imp;

import org.example.enums.DiscountType;
import org.example.factroy.DiscountFactory;
import org.example.strategy.discount.DiscountStrategy;
import org.example.strategy.discount.FixedDiscount;
import org.example.strategy.discount.PercentageDiscount;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DiscountFactoryImpl implements DiscountFactory {
    private final Map<DiscountType, Supplier<DiscountStrategy>> registry = new HashMap<>();
    private static final BigDecimal PERCENTAGE_AMOUNT = BigDecimal.valueOf(0.1);
    private static final BigDecimal FIXED_AMOUNT = BigDecimal.valueOf(100);


    public DiscountFactoryImpl() {
        register(DiscountType.PERCENTAGE,()->new PercentageDiscount(PERCENTAGE_AMOUNT));
        register(DiscountType.FIXED, ()-> new FixedDiscount(FIXED_AMOUNT));
    }

    public void register(DiscountType type, Supplier< DiscountStrategy> supplier) {
        registry.put(type, supplier);
    }

    @Override
    public DiscountStrategy createDiscount(DiscountType discountType) {
        Supplier<DiscountStrategy>  strategy = registry.get(discountType);
        if (strategy == null) {
            throw new IllegalArgumentException(ErrorMessages.UNKNOWN_DISCOUNT_TYPE);
        }
        return strategy.get();
    }
}
