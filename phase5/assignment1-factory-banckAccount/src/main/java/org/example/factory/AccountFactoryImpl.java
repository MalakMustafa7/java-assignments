package org.example.factory;

import org.example.enums.AccountType;
import org.example.model.BankAccount;
import org.example.model.BusinessAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public class AccountFactoryImpl implements  AccountFactory{
    @Override
    public BankAccount createAccount(AccountType type, String owner, BigDecimal balance) {
        switch (type){
            case SAVINGS -> {
                return new SavingsAccount(owner, balance, 0.05);
            }
            case CHECKING -> {
                return new CheckingAccount(owner,balance);
            }
            case BUSINESS -> {
                return new BusinessAccount(owner,balance,"TechComp");
            }
            default -> {
                throw new IllegalArgumentException(ErrorMessages.UNKNOWN_TYPE);
            }
        }
    }
}
