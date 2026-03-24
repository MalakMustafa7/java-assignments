package org.example.validation;

import org.example.model.PaymentDetails;

public class PaymentValidator {
    public static boolean validateEmail(PaymentDetails details){
        if(details == null){
            return false;
        }
        String email = details.getMetadata().get("email");
        return email != null && !email.isBlank();

    }

    public static boolean validateWallet(PaymentDetails details) {
        if(details==null){
            return false;
        }
        String wallet = details.getMetadata().get("walletAddress");
        return wallet != null && !wallet.isBlank();
    }

    public static boolean validateCard(PaymentDetails details){
        if(details==null){
            return false;
        }
        String cardNumber = details.getMetadata().get("cardNumber");
        if(cardNumber == null || cardNumber.isBlank()){
            return false;
        }
        String cvv = details.getMetadata().get("cvv");
        return cvv != null && !cvv.isBlank();
    }

    public static boolean validateApplePay(PaymentDetails details){
        if(details == null) {
            return false;
        }
        String accountId = details.getMetadata().get("accountId");
        return accountId != null && !accountId.isBlank();
    }
}

