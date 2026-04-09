package org.example.Validation.impl;

import org.example.Validation.CustomerValidator;
import org.example.exception.InSufficientBalanceException;
import org.example.model.Customer;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public class CustomerValidatorImpl implements CustomerValidator {
    @Override
    public void validateCustomerData(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException(ErrorMessages.CUSTOMER_NULL);
        }
        validateName(customer.getName());
        validateRegion(customer.getRegion());
        validateBalance(customer.getBalance());
    }

    @Override
    public void validateSufficientBalance(BigDecimal balance, BigDecimal amount) {
        if(balance.compareTo(amount)<0){
            throw new InSufficientBalanceException(ErrorMessages.INSUFFICIENT_BALANCE);
        }
    }


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NAME);
        }
    }

    private void validateRegion(String region) {
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_REGION);
        }
    }

    private void validateBalance(BigDecimal balance) {
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(ErrorMessages.NEGATIVE_BALANCE);
        }
    }
}
