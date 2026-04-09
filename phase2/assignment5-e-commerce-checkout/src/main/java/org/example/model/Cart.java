package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private final Long cartId;
    private final Long customerId;
    private Map<Long,CartItem> cartItems ;
    public Cart(Long cartId, Long customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.cartItems = new HashMap<>();
    }
}
