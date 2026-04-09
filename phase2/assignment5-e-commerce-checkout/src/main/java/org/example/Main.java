package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.Validation.CartValidator;
import org.example.Validation.CustomerValidator;
import org.example.Validation.PaymentValidator;
import org.example.Validation.ProductValidator;
import org.example.Validation.impl.CartValidatorImpl;
import org.example.Validation.impl.CustomerValidatorImpl;
import org.example.Validation.impl.PaymentValidatorImpl;
import org.example.Validation.impl.ProductValidatorImpl;
import org.example.enums.DiscountType;
import org.example.enums.PaymentType;
import org.example.factroy.DiscountFactory;
import org.example.factroy.PaymentFactory;
import org.example.factroy.TaxFactory;
import org.example.factroy.imp.DiscountFactoryImpl;
import org.example.factroy.imp.PaymentFactoryImpl;
import org.example.factroy.imp.TaxFactoryImpl;
import org.example.model.*;
import org.example.pipeline.CheckoutHandler;
import org.example.pipeline.CheckoutPipeline;
import org.example.pipeline.handlers.*;
import org.example.repository.CartRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repository.impl.CartRepositoryImpl;
import org.example.repository.impl.CustomerRepositoryImpl;
import org.example.repository.impl.OrderRepositoryImpl;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.service.*;
import org.example.service.impl.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        // --- Repositories ---
        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        CartRepository cartRepo     = new CartRepositoryImpl();
        OrderRepository orderRepo    = new OrderRepositoryImpl();
        ProductRepository productRepo  = new ProductRepositoryImpl();

        // --- Validators ---
        CustomerValidator customerValidator = new CustomerValidatorImpl();
        PaymentValidator paymentValidator  = new PaymentValidatorImpl();
        ProductValidator productValidator  = new ProductValidatorImpl();
        CartValidator cartValidator     = new CartValidatorImpl();

        // --- Services ---
        CustomerService customerService = new CustomerServiceImpl(customerRepo, customerValidator);
        ProductService productService  = new ProductServiceImpl(productRepo, productValidator);
        CartService cartService     = new CartServiceImpl(cartRepo, productService, cartValidator);

        // --- Factories ---
        DiscountFactory discountFactory = new DiscountFactoryImpl();
        TaxFactory taxFactory      = new TaxFactoryImpl();
        PaymentFactory paymentFactory  = new PaymentFactoryImpl(paymentValidator, customerService);

        // --- Notification ---
        NotificationService notificationService = new SMSNotification();

        // --- Pipeline ---
        List<CheckoutHandler> handlers = new ArrayList<>();
        handlers.add(new CartValidationHandler(cartValidator));
        handlers.add(new DiscountHandler(discountFactory));
        handlers.add(new TaxHandler(taxFactory));
        handlers.add(new PaymentHandler(paymentFactory, cartService, productService, orderRepo));
        handlers.add(new NotificationHandler(notificationService));
        CheckoutPipeline pipeline = new CheckoutPipeline(handlers);

        // --- CheckoutService ---
        CheckoutService checkoutService = new CheckoutServiceImpl(
                customerService, cartService, pipeline
        );

        // --- Data Setup ---
        Customer alice = new Customer(1L, "Alice", "US", BigDecimal.valueOf(1000));
        Customer bob   = new Customer(2L, "Bob",   "EU", BigDecimal.valueOf(500));
        Customer john  = new Customer(3L, "John",  "SA", BigDecimal.valueOf(2000));
        customerService.addCustomer(alice);
        customerService.addCustomer(bob);
        customerService.addCustomer(john);

        cartRepo.save(new Cart(1L, alice.getCustomerId()));
        cartRepo.save(new Cart(2L, bob.getCustomerId()));
        cartRepo.save(new Cart(3L, john.getCustomerId()));

        Product laptop   = new Product(101L, "Laptop",   BigDecimal.valueOf(500), 3);
        Product mouse    = new Product(102L, "Mouse",    BigDecimal.valueOf(50),  5);
        Product keyboard = new Product(103L, "Keyboard", BigDecimal.valueOf(700), 1);
        productService.addProduct(laptop);
        productService.addProduct(mouse);
        productService.addProduct(keyboard);

        cartService.addItem(alice.getCustomerId(), laptop, 2);
        cartService.addItem(alice.getCustomerId(), mouse,  2);
        cartService.addItem(bob.getCustomerId(),   mouse,  1);
        cartService.addItem(john.getCustomerId(),  keyboard, 1);

        log.info("Scenario 1: Alice — CreditCard + Percentage (US tax)");
        CheckoutResult r1 = checkoutService.checkOut(new CheckoutRequest(
                alice.getCustomerId(), PaymentType.CREDIT_CARD, DiscountType.PERCENTAGE, "USD"
        ));
        log.info("Alice:  {}", r1);

        log.info("Scenario 2: Bob — GiftCard + Percentage (EU VAT)");
        CheckoutResult r2 = checkoutService.checkOut(new CheckoutRequest(
                bob.getCustomerId(), PaymentType.GIFT_CARD, DiscountType.PERCENTAGE, "USD"
        ));
        log.info("Bob:    {}", r2);

        log.info("Scenario 3: John — PayPal + Fixed (No tax)");
        CheckoutResult r3 = checkoutService.checkOut(new CheckoutRequest(
                john.getCustomerId(), PaymentType.PAYPAL, DiscountType.FIXED, "USD"
        ));
        log.info("John:   {}", r3);
    }
}