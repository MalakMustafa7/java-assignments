package org.example.strategy.tax;

import java.math.BigDecimal;

public class NoTax implements TaxCalculator {
    @Override
    public BigDecimal calculateTax(BigDecimal subTotal) {
        return BigDecimal.ZERO;
    }
}
