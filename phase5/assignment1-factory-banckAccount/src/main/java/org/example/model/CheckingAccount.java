package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TransactionType;
import org.example.validation.AccountValidator;

import java.math.BigDecimal;
@Getter
@Setter
public class CheckingAccount extends BankAccount{
   private double overdraftLimit ;
    public CheckingAccount( String ownerName, BigDecimal balance) {
        super( ownerName, balance);
        this.overdraftLimit = 500;
    }
    public CheckingAccount( String ownerName, BigDecimal balance,double overdraftLimit) {
        super( ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public void withdraw(double amount) {
        AccountValidator.validateAmount(amount);
        BigDecimal newBalance = balance.subtract(BigDecimal.valueOf(amount));
        AccountValidator.validateOverdraftLimit(newBalance,getOverdraftLimit());
        balance = newBalance;
        log(TransactionType.WITHDRAW, amount);

    }
}
