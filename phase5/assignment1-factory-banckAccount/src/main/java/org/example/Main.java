package org.example;

import org.example.enums.AccountType;
import org.example.model.BankAccount;
import org.example.repository.Bank;
import org.example.model.BusinessAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;
import org.example.service.AccountService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        AccountService accountService = new AccountService();
        BankAccount savings = accountService.createAccount(AccountType.SAVINGS,"Malak",BigDecimal.valueOf(1000));
        BankAccount checking = accountService.createAccount(AccountType.CHECKING,"Ali",BigDecimal.valueOf(500));
        BankAccount business = accountService.createAccount(AccountType.BUSINESS,"Omar",BigDecimal.valueOf(2000));
        



        bank.totalAssetsReport();

        System.out.println("==================================");

       System.out.println(bank.findByOwner("malak"));



    }
}