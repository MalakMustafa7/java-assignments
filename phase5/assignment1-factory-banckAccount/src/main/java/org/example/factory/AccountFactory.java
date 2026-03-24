package org.example.factory;

import org.example.enums.AccountType;
import org.example.model.BankAccount;

import java.math.BigDecimal;

public interface AccountFactory {
    BankAccount createAccount(AccountType type,String owner , BigDecimal balance);
}
