package org.example.repository.impl;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<Long,Customer> customers = new HashMap<>();
    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getCustomerId(),customer);
    }
}
