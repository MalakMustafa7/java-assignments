package org.example.model;

import org.example.enums.TransactionType;
import org.example.validation.AccountValidator;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount{
    private final double interestRate;
    public SavingsAccount( String ownerName, BigDecimal balance,double interestRate) {
        super( ownerName, balance);
        this.interestRate = interestRate;
    }

    public void applyInterest(){
        BigDecimal interest = balance.multiply(BigDecimal.valueOf(interestRate));
        balance = balance.add(interest);
        log(TransactionType.INTEREST, interest.doubleValue());
    }
    public void applyMonthlyCompoundInterest(int months){
        AccountValidator.validateMonths(months);
        double monthlyRate = interestRate/12;
       for(int i=0;i<months;i++){
          BigDecimal interest = balance.multiply(BigDecimal.valueOf(monthlyRate));
          balance = balance.add(interest);
          log(TransactionType.INTEREST, interest.doubleValue());
      }
    }


}
