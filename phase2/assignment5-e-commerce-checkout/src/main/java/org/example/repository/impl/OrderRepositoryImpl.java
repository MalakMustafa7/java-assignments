package org.example.repository.impl;

import org.example.model.Order;
import org.example.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    @Override
    public void save(Order order) {
        if (order.getOrderId() == null) {
            order.setOrderId(idGenerator.getAndIncrement());
        }
        orders.put(order.getOrderId(),order);

    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .toList();
    }
}
