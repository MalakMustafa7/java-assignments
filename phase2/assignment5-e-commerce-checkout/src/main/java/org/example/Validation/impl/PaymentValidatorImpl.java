package org.example.Validation.impl;

import org.example.Validation.PaymentValidator;
import org.example.model.PaymentDetails;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public class PaymentValidatorImpl implements PaymentValidator {
    @Override
    public void validatePaymentDetails(PaymentDetails paymentDetails) {
        if (paymentDetails == null) {
            throw new IllegalArgumentException(ErrorMessages.PAYMENT_DETAILS_NULL);
        }
        validateAmount(paymentDetails.getAmount());
        validateCurrency(paymentDetails.getCurrency());
        validateCustomerId(paymentDetails.getCustomerId());

    }
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_AMOUNT);
        }
    }

    private void validateCurrency(String currency) {
        if (currency == null || currency.trim().length() != 3) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_CURRENCY);
        }
    }
    private void validateCustomerId(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException(ErrorMessages.CUSTOMER_ID_REQUIRED);
        }
    }
}
