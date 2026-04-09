package org.example.repository;

import org.example.model.Cart;
import org.example.model.CartItem;

import java.util.Optional;

public interface CartRepository {
   void save(Cart cart);
   void delete(Long customerId);
   Optional<Cart> findByCustomerId(Long customerId);

}
