package org.example.strategy.impl;

import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.strategy.PaymentStrategy;
import org.example.utility.ErrorMessages;
import org.example.validation.PaymentValidator;

public class ApplePayStrategy implements PaymentStrategy {

    @Override
    public boolean validate(PaymentDetails details) {
      return PaymentValidator.validateApplePay(details);
    }

    @Override
    public PaymentResult process(PaymentDetails details) {
        if(!validate(details)){
            return new PaymentResult(false, ErrorMessages.VALIDATION_FAILED);
        }
        return new PaymentResult(true, ErrorMessages.APPLE_PAY_SUCCESS);
    }
}
