package org.example.factory;

import org.example.strategy.PaymentStrategy;

public interface PaymentFactory {
    PaymentStrategy createPayment(String type);
}
