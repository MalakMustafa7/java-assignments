package org.example.pipeline.handlers;

import org.example.enums.TaxType;
import org.example.factroy.TaxFactory;
import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;
import org.example.strategy.tax.TaxCalculator;

public class TaxHandler extends CheckoutHandler {
    private final TaxFactory taxFactory;
    public TaxHandler(TaxFactory taxFactory){
        this.taxFactory = taxFactory;
    }
    @Override
    protected void process(CheckoutContext context) {
        TaxType taxType = TaxType.fromRegion(context.getCustomer().getRegion());
        TaxCalculator calculator = taxFactory.createTaxCalculator(taxType);
        context.setTax(calculator.calculateTax(context.getSubtotal()));
        context.setTotal(context.getSubtotal()
                         .subtract(context.getDiscount())
                          .add(context.getTax()));
    }
}
