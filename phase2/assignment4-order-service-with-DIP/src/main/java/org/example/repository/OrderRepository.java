package org.example.repository;

import org.example.enums.Status;
import org.example.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    List<Order>findAll();
    Optional<Order>findById(String orderId);
    List<Order>findByCustomerId(String customerId);
    List<Order>findByStatus(Status status);
    void deleteById(String orderId);

}
