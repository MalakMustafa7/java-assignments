package org.example.Validation;

import org.example.model.Customer;

import java.math.BigDecimal;

public interface CustomerValidator {
    void validateCustomerData(Customer customer);
    void validateSufficientBalance(BigDecimal balance, BigDecimal amount);
}
