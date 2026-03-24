package org.example.repository.impl;

import org.example.enums.Status;
import org.example.model.Order;
import org.example.repository.OrderRepository;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String,Order>orderMap = new HashMap<>();

    @Override
    public void save(Order order) {
        orderMap.put(order.getOrderId(),order);
    }

    @Override
    public List<Order> findAll() {
        return orderMap.values().stream().toList();
    }


    @Override
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orderMap.get(orderId)) ;
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return orderMap.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .toList();
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return orderMap.values().stream()
                .filter(order -> order.getOrderStatus()== status)
                .toList();
    }

    @Override
    public void deleteById(String orderId) {
        orderMap.remove(orderId);
    }
}
