package org.example.service;

import org.example.enums.AccountType;
import org.example.factory.AccountFactory;
import org.example.factory.AccountFactoryImpl;
import org.example.model.BankAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;

import java.math.BigDecimal;

public class AccountService {

    public BankAccount createAccount(AccountType type, String owner, BigDecimal balance){
        AccountFactoryImpl accountFactory = new AccountFactoryImpl();
        return accountFactory.createAccount(type,owner,balance);

    }
    public void deposit(BankAccount account, double amount){
        account.deposit(amount);
    }



    public void transfer(BankAccount source, BankAccount target, double amount){
        source.transfer(target, amount);
    }

}
