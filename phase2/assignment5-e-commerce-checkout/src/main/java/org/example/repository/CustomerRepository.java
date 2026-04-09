package org.example.repository;

import org.example.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
    void save(Customer customer);
}
