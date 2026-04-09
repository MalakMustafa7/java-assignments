package org.example.Validation;

import org.example.model.PaymentDetails;

import java.math.BigDecimal;

public interface PaymentValidator {
    void validatePaymentDetails(PaymentDetails paymentDetails);
}
