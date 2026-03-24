package org.example.service;

import org.example.enums.Status;
import org.example.model.Order;
import org.example.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order placeOrder(String customerId, List<OrderItem> orderItems);
    void cancelOrder(String orderId);
    void updateStatus(String orderId, Status status);
    BigDecimal getOrderSummary(String orderId);
    Order findOrderById(String orderId);

}
