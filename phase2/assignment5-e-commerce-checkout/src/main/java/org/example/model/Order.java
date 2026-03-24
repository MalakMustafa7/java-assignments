package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private final Long orderId;
    private  Long customerId;
    private List<CartItem>cartItems;
    private BigDecimal subtotal;
    private BigDecimal total;
    private double tax;

}
