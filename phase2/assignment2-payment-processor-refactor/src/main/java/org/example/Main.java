package org.example;

import org.example.factory.PaymentFactory;
import org.example.factory.impl.PaymentFactoryImpl;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.processor.PaymentProcessor;
import org.example.service.PaymentService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        PaymentFactory paymentFactory = new PaymentFactoryImpl();
        PaymentService paymentService = new PaymentService(paymentFactory,paymentProcessor);

        Map<String, String> paypalMetadata = new HashMap<>();
        paypalMetadata.put("email", "user@example.com");
        PaymentDetails paypalDetails = new PaymentDetails(new BigDecimal("1000"), "USD", paypalMetadata);

        Map<String, String> appleMetadata = new HashMap<>();
        appleMetadata.put("accountId", "apple123");
        PaymentDetails appleDetails = new PaymentDetails(new BigDecimal("2500"), "USD", appleMetadata);

        PaymentResult paypalResult = paymentService.pay("PayPal", paypalDetails);

        PaymentResult appleResult = paymentService.pay("applepay", appleDetails);


        System.out.println("\nTransaction History:");
        paymentProcessor.getTransactionHistory().forEach(System.out::println);





    }
}