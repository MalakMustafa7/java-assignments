package org.example.pipeline.handlers;

import org.example.Validation.CartValidator;
import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;

public class CartValidationHandler extends CheckoutHandler {
    private final CartValidator cartValidator;
    public CartValidationHandler(CartValidator cartValidator){
        this.cartValidator = cartValidator;
    }
    @Override
    protected void process(CheckoutContext context) {
        cartValidator.validateCartNotEmpty(context.getCart());
    }
}
