package org.example.strategy.payment;

import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;

public interface PaymentGateway {
    PaymentResult processPayment(PaymentDetails paymentDetails);

}
