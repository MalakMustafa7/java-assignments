package org.example.processor;

import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.strategy.PaymentStrategy;
import org.example.utility.ErrorMessages;
import org.example.validation.PaymentDetailsValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentProcessor {
    private final List<PaymentResult> transactionHistory = new ArrayList<>();

    public PaymentResult process(PaymentStrategy paymentStrategy, PaymentDetails paymentDetails){
        PaymentDetailsValidator.validate(paymentDetails);
        PaymentResult result = executeWithRetry(paymentStrategy,paymentDetails);
        transactionHistory.add(result);
        return result;
    }

    public List<PaymentResult> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
    private PaymentResult executeWithRetry(PaymentStrategy strategy, PaymentDetails details){
        PaymentResult result;
        int retries = 0;
        while (retries < 3){
            result = strategy.process(details);
            if(result.isSuccess()){
                return result;
            }
            try {
                Thread.sleep((long) Math.pow(2, retries) * 1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
            retries++;
        }
        return new PaymentResult(false, ErrorMessages.VALIDATION_FAILED_AFTER_RETRIES);
    }
}
