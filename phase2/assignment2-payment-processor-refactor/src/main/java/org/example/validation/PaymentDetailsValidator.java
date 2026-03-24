package org.example.validation;

import org.example.model.PaymentDetails;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PaymentDetailsValidator {
    public static  void validateAmount(BigDecimal amount){
        if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException(ErrorMessages.AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public static void validateCurrency(String currency){
        if(currency==null || currency.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.VALID_CURRENCY);
        }
    }
    public static void validateMetadata(Map<String,String> metadata) {
        if (metadata == null) {
            throw new IllegalArgumentException(ErrorMessages.METADATA_NULL);
        }
    }

    public static void validate(PaymentDetails details){
        validateAmount(details.getAmount());
        validateCurrency(details.getCurrency());
        validateMetadata(details.getMetadata());
    }

}
