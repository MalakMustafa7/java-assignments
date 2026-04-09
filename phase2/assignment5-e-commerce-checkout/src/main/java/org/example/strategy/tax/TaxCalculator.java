package org.example.strategy.tax;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal calculateTax(BigDecimal subTotal);
}
