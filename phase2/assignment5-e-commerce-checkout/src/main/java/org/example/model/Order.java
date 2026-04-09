package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Order {
    private  Long orderId;
    private  Long customerId;
    private List<CartItem>cartItems;
    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal tax;

}
