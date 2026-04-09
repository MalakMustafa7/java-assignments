package org.example.service.impl;

import org.example.enums.DiscountType;
import org.example.enums.PaymentType;
import org.example.enums.TaxType;
import org.example.exception.PaymentFailedException;
import org.example.factroy.DiscountFactory;
import org.example.factroy.PaymentFactory;
import org.example.factroy.TaxFactory;
import org.example.model.*;
import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;
import org.example.pipeline.CheckoutPipeline;
import org.example.repository.OrderRepository;
import org.example.service.*;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutServiceImpl implements CheckoutService {
    private final boolean completed = true;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CheckoutPipeline pipeline;

    public CheckoutServiceImpl(CustomerService customerService,
            CartService cartService,
            CheckoutPipeline pipeline) {
        this.customerService = customerService;
        this.cartService= cartService;
        this.pipeline = pipeline;
    }
    @Override
    public CheckoutResult checkOut(CheckoutRequest checkoutRequest) {
        Customer customer = customerService.getCustomerById(checkoutRequest.getCustomerId());
        Cart cart = cartService.getCartByCustomerId(checkoutRequest.getCustomerId());
        CheckoutContext context = new CheckoutContext(checkoutRequest, customer, cart);
        context.setSubtotal(cartService.calculateSubtotal(cart));
        pipeline.execute(context);
        return new CheckoutResult(
                context.getCartItems(),
                context.getSubtotal(),
                context.getTax(),
                context.getDiscount(),
                context.getTotal(),
                checkoutRequest.getPaymentType(),
                completed
        );
    }


}
