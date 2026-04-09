package org.example.pipeline.handlers;

import org.example.exception.PaymentFailedException;
import org.example.factroy.PaymentFactory;
import org.example.model.CartItem;
import org.example.model.Order;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;
import org.example.repository.OrderRepository;
import org.example.service.CartService;
import org.example.strategy.payment.PaymentGateway;
import org.example.service.ProductService;

import java.util.List;

public class PaymentHandler extends CheckoutHandler {
    private final PaymentFactory paymentFactory;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    public PaymentHandler(PaymentFactory paymentFactory ,
                          CartService cartService,
                          ProductService productService,
                          OrderRepository orderRepository){
        this.paymentFactory = paymentFactory;
        this.cartService = cartService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    protected void process(CheckoutContext context) {
        PaymentGateway gateway = paymentFactory.createPayment(context.getCheckoutRequest().getPaymentType());
        PaymentResult result = gateway.processPayment(new PaymentDetails(context.getTotal(),
                                                                         context.getCheckoutRequest().getCurrency(),
                                                                         context.getCheckoutRequest().getCustomerId()));
        if (!result.isSuccess()) {
            throw new PaymentFailedException(result.getMessage());
        }
        List<CartItem> cartItems= cartService.getItems(context.getCheckoutRequest().getCustomerId());
        cartItems.forEach(cartItem ->productService.decreaseQuantity(cartItem.getProduct().getProductId(),
                cartItem.getQuantity()));
        context.setCartItems(cartItems);
        Order order = new Order(null, context.getCheckoutRequest().getCustomerId(),
                                cartItems, context.getSubtotal(), context.getTotal(), context.getTax());
        orderRepository.save(order);
        context.setOrder(order);
        cartService.clearCart(context.getCheckoutRequest().getCustomerId());

    }
}
