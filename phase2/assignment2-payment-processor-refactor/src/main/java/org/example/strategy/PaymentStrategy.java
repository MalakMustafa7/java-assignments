package org.example.strategy;

import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;

public interface PaymentStrategy {
    boolean validate(PaymentDetails details);
    PaymentResult process(PaymentDetails details);


}
