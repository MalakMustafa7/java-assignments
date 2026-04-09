package org.example.strategy.payment;

import org.example.Validation.PaymentValidator;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.utility.PaymentMessages;

public class CreditCard implements PaymentGateway {
    private final PaymentValidator paymentValidator;
    public CreditCard(PaymentValidator paymentValidator){
        this.paymentValidator = paymentValidator;
    }
    @Override
    public PaymentResult processPayment(PaymentDetails paymentDetails) {
        try {
            paymentValidator.validatePaymentDetails(paymentDetails);
            return new PaymentResult(true, PaymentMessages.CREDIT_CARD_SUCCESS);
        }catch (IllegalArgumentException e){
            return new PaymentResult(false, e.getMessage());
        }
    }
}
