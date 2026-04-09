package org.example.pipeline.handlers;

import org.example.factroy.DiscountFactory;
import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;
import org.example.strategy.discount.DiscountStrategy;

public class DiscountHandler extends CheckoutHandler {
    private final DiscountFactory discountFactory;
    public DiscountHandler(DiscountFactory discountFactory){
        this.discountFactory = discountFactory;
    }
    @Override
    protected void process(CheckoutContext context) {
        DiscountStrategy strategy = discountFactory.createDiscount(context.getCheckoutRequest().getDiscountType());
        context.setDiscount(strategy.calculateDiscountAmount(context.getCart()));
    }
}
