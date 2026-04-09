package org.example.model;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PaymentResult {
    private boolean success;
    private String transactionId;
    private String message;

    public PaymentResult(boolean success,String message){
        this.success = success;
        this.message = message;
        this.transactionId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "PaymentResult{" +
                ", transactionId='" + transactionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
