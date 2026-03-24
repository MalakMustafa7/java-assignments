package org.example.model;

import lombok.Getter;
import org.example.enums.TransactionType;
import org.example.validation.AccountValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public abstract class BankAccount {
    private final Long accountNumber;
    private final String ownerName;
    protected BigDecimal balance;
    protected List<String> transactionHistory  ;

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final AtomicLong counter = new AtomicLong(0);

    public BankAccount( String ownerName, BigDecimal balance) {
        this.accountNumber =  counter.incrementAndGet();
        this.ownerName = ownerName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }


    public void deposit(double amount){
       AccountValidator.validateAmount(amount);
        balance= balance.add(BigDecimal.valueOf(amount));
        log(TransactionType.DEPOSIT , amount);
    }
    protected void withdraw(double amount){
        AccountValidator.validateAmount(amount);
        AccountValidator.validateSufficientBalance(balance,amount);
        balance = balance.subtract(BigDecimal.valueOf(amount));
        log(TransactionType.WITHDRAW , amount);
    }
    public void executeWithdraw(double amount){
       withdraw(amount);
    }
    public void transfer(BankAccount target,double amount){
       AccountValidator.validateTarget(this,target);
        this.withdraw(amount);
        target.deposit(amount);
        log(TransactionType.TRANSFER , amount);

    }
    protected void log(TransactionType type, double amount){
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = String.format("%-20s | %-15s | %-10.2f | %-10.2f",
                timestamp, type, amount, balance);
        transactionHistory.add(entry);
    }



    public void printStatement() {
        System.out.println("\n==============================================");
        System.out.println("Account Statement");
        System.out.println("Account: " + accountNumber);
        System.out.println("Owner  : " + ownerName);
        System.out.println("Balance: " + balance);
        System.out.println("----------------------------------------------");
        System.out.printf("%-20s | %-15s | %-10s | %-10s\n",
                "Timestamp", "Type", "Amount", "Balance");
        System.out.println("----------------------------------------------");

        transactionHistory.forEach(System.out::println);
        System.out.println("==============================================\n");
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
