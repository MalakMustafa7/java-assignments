package org.example.service.impl;

import org.example.Validation.CustomerValidator;
import org.example.exception.InSufficientBalanceException;
import org.example.exception.NotFoundException;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerValidator customerValidator){
        this.customerRepository = customerRepository;
        this.customerValidator = customerValidator;
    }
    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new NotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
    }



    @Override
    public void deductBalance(Long customerId, BigDecimal amount) {
        Customer customer = getCustomerById(customerId);
        customerValidator.validateSufficientBalance(customer.getBalance(),amount);
        customer.setBalance(customer.getBalance().subtract(amount));
        customerRepository.save(customer);

    }

    @Override
    public void addCustomer(Customer customer) {
        customerValidator.validateCustomerData(customer);
        customerRepository.save(customer);

    }


}
