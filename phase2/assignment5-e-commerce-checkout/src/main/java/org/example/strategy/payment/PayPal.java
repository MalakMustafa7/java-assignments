package org.example.strategy.payment;

import org.example.Validation.PaymentValidator;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.utility.PaymentMessages;

public class PayPal implements PaymentGateway {
    private final PaymentValidator paymentValidator;
    public PayPal(PaymentValidator paymentValidator){
        this.paymentValidator = paymentValidator;
    }
    @Override
    public PaymentResult processPayment(PaymentDetails paymentDetails) {
        try {
            paymentValidator.validatePaymentDetails(paymentDetails);
            return new PaymentResult(true, PaymentMessages.PAYPAL_SUCCESS);
        }catch (IllegalArgumentException e){
            return new PaymentResult(false, e.getMessage());
        }
    }
}
