package org.example.repository;

import org.example.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(Long orderId);
    List<Order> findAllByCustomerId(Long customerId);
}
