package org.example.repository;

import org.example.model.BankAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount  bankAccount){
        accounts.add(bankAccount);
    }

    public List<BankAccount> findByOwner(String ownerName) {
        return accounts.stream()
                .filter(bankAccount -> bankAccount.getOwnerName().equalsIgnoreCase(ownerName))
                .toList();
    }
    public void totalAssetsReport(){
        BigDecimal total =BigDecimal.ZERO;
        for (BankAccount account : accounts) {
            total = total.add(account.getBalance());
        }
        System.out.println("Total Bank Assets: " + total);
    }
}
