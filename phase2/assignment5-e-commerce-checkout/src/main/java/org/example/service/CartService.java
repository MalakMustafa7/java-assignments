package org.example.service;

import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addItem(Long customerId, Product product,int quantity);
    void removeItem(Long customerId,Long productId);
    List<CartItem> getItems(Long userId);
    Cart getCartByCustomerId(Long customerId);
    BigDecimal calculateSubtotal(Cart cart);
    void clearCart(Long customerId);

}
