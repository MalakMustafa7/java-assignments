package org.example.factory.impl;

import org.example.factory.PaymentFactory;
import org.example.strategy.PaymentStrategy;
import org.example.strategy.impl.ApplePayStrategy;
import org.example.strategy.impl.CreditCardStrategy;
import org.example.strategy.impl.CryptoStrategy;
import org.example.strategy.impl.PayPalStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PaymentFactoryImpl implements PaymentFactory {
    private static final Map<String, Supplier<PaymentStrategy>> registry = new HashMap<>();
    static {

        registerStrategy("paypal", PayPalStrategy::new);
        registerStrategy("creditcard", CreditCardStrategy::new);
        registerStrategy("crypto", CryptoStrategy::new);
        registerStrategy("applepay", ApplePayStrategy::new);
    }
    public static void registerStrategy(String type , Supplier<PaymentStrategy> strategySupplier){
        registry.put(type.toLowerCase(),strategySupplier);
    }
    public PaymentStrategy createPayment(String type){
        Supplier<PaymentStrategy> supplier = registry.get(type.toLowerCase());
        if(supplier==null){
            throw new IllegalArgumentException("UnKnown type: "+type);
        }
        return supplier.get();
    }
}
