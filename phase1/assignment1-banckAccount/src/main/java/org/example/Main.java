package org.example;

import org.example.repository.Bank;
import org.example.model.BusinessAccount;
import org.example.model.CheckingAccount;
import org.example.model.SavingsAccount;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        SavingsAccount s = new SavingsAccount( "Malak", BigDecimal.valueOf(1000), 0.05);
        CheckingAccount c = new CheckingAccount( "Ali", BigDecimal.valueOf(500));
        BusinessAccount b = new BusinessAccount( "Omar", BigDecimal.valueOf(2000), "TechComp");

        bank.addAccount(s);
        bank.addAccount(c);
        bank.addAccount(b);

        s.deposit(200);
        s.applyInterest();

        c.withdraw(800);

        b.withdraw(2500);
        b.chargeFee();

        s.transfer(c, 100);

        s.printStatement();
        c.printStatement();
        b.printStatement();


        bank.totalAssetsReport();

        System.out.println("==================================");

       System.out.println(bank.findByOwner("malak"));



    }
}