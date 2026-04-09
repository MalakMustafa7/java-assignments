package org.example.factroy.imp;

import org.example.enums.TaxType;
import org.example.factroy.TaxFactory;
import org.example.strategy.tax.TaxCalculator;
import org.example.strategy.tax.EUVatCalculator;
import org.example.strategy.tax.NoTax;
import org.example.strategy.tax.USTaxCalculator;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TaxFactoryImpl implements TaxFactory {
    private final Map<TaxType, Supplier<TaxCalculator>> registry = new HashMap<>();
    private static final BigDecimal US_TAX = BigDecimal.valueOf(.1);
    private static final BigDecimal EUV_TAX = BigDecimal.valueOf(.2);
    public TaxFactoryImpl() {
        register(TaxType.US,()->new USTaxCalculator(US_TAX));
        register(TaxType.EU,()->new EUVatCalculator(EUV_TAX));
        register(TaxType.NONE, NoTax::new);
    }

    public void register(TaxType type, Supplier<TaxCalculator> supplier) {
        registry.put(type, supplier);
    }
    @Override
    public TaxCalculator createTaxCalculator(TaxType type) {
        Supplier<TaxCalculator> calculator = registry.get(type);
        if (calculator == null) {
            throw new IllegalArgumentException(ErrorMessages.UNKNOWN_REGION);
        }
        return calculator.get();
    }
}
