package org.example.service;

import org.example.model.Customer;

import java.math.BigDecimal;

public interface CustomerService {
    Customer getCustomerById(Long customerId);
    void deductBalance(Long customerId, BigDecimal amount);
    void addCustomer(Customer customer);



}
