package org.example.validation;

import org.example.model.BankAccount;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public final class AccountValidator {
    public static void validateAmount(double amount){
        if(amount <=0){
            throw  new IllegalArgumentException(ErrorMessages.AMOUNT_MUST_BE_POSITIVE);
        }
    }
    public static void validateSufficientBalance(BigDecimal balance, double amount) {
        if (balance.compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new IllegalArgumentException(ErrorMessages.INSUFFICIENT_BALANCE);
        }
    }

    public static void validateTarget(BankAccount source,BankAccount target){
        if (target == null) {
            throw new IllegalArgumentException(ErrorMessages.TARGET_ACCOUNT_NULL);
        }
        if (source == target) {
            throw new IllegalArgumentException(ErrorMessages.SAME_ACCOUNT_TRANSFER);
        }
    }

    public static void validateOverdraftLimit(BigDecimal newBalance,double overdraftLimit){
        if(newBalance.compareTo(BigDecimal.valueOf(-overdraftLimit))<0){
            throw new IllegalArgumentException(ErrorMessages.OVERDRAFT_EXCEEDED);
        }
    }

    public static void validateMonths(int months){
        if(months<0){
            throw new IllegalArgumentException(ErrorMessages.MONTHS_NEGATIVE);
        }
    }
}
