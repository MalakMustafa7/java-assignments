package org.example.service;

import org.example.factory.PaymentFactory;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.processor.PaymentProcessor;
import org.example.strategy.PaymentStrategy;

public class PaymentService {
    private final PaymentFactory paymentFactory;
    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentFactory paymentFactory,PaymentProcessor paymentProcessor){
        this.paymentFactory = paymentFactory;
        this.paymentProcessor = paymentProcessor;
    }

    public PaymentResult pay(String type, PaymentDetails paymentDetails){
        PaymentStrategy strategy = paymentFactory.createPayment(type);
        return paymentProcessor.process(strategy,paymentDetails);
    }

}
