package org.example.service.Impl;


import org.example.enums.Status;
import org.example.exception.NotFoundException;
import org.example.exception.OrderAlreadyCancelledException;
import org.example.exception.OrderStatusAlreadySetException;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public Order placeOrder(String customerId, List<OrderItem>orderItems) {
        Order order = new Order(customerId,orderItems);

        orderRepository.save(order);

        return order;
    }

    @Override
    public void cancelOrder(String orderId) {
       Order order = findOrderById(orderId);
       if(order.getOrderStatus() == Status.CANCELLED ){
           throw new OrderAlreadyCancelledException(ErrorMessages.ORDER_ALREADY_CANCELLED);
       }
       order.setOrderStatus(Status.CANCELLED);
       orderRepository.save(order);
    }

    @Override
    public void updateStatus(String orderId, Status status) {
        Order order = findOrderById(orderId);
        if(order.getOrderStatus() == Status.CANCELLED ){
            throw new OrderAlreadyCancelledException(ErrorMessages.ORDER_ALREADY_CANCELLED);
        }
        if(order.getOrderStatus() == status ){
            throw new OrderStatusAlreadySetException(ErrorMessages.ORDER_STATUS_ALREADY_SET);
        }
        order.setOrderStatus(status);
        orderRepository.save(order);


    }

    @Override
    public BigDecimal getOrderSummary(String orderId) {
       Order order= findOrderById(orderId);
        return order.getOrderItems().stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Override
    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()->new NotFoundException(ErrorMessages.Order_NOT_FOUND));
    }



}
