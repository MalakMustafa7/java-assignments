package org.example.factroy;

import org.example.enums.PaymentType;
import org.example.strategy.payment.PaymentGateway;

public interface PaymentFactory {
    PaymentGateway createPayment(PaymentType paymentType);
}
