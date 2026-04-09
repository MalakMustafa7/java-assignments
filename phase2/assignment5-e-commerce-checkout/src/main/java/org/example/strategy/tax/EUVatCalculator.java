package org.example.strategy.tax;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
public class EUVatCalculator implements TaxCalculator {
    private final BigDecimal taxRate;
    @Override
    public BigDecimal calculateTax(BigDecimal subTotal) {
        return subTotal.multiply(taxRate);
    }
}
