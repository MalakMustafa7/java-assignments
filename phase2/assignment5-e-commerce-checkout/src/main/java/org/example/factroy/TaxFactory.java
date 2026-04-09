package org.example.factroy;

import org.example.enums.TaxType;
import org.example.strategy.tax.TaxCalculator;

public interface TaxFactory {
    TaxCalculator createTaxCalculator(TaxType type);
}
