package org.example.repository.impl;

import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepository {
    private final Map<Long,Cart> cartMap = new HashMap<>();
    @Override
    public void save(Cart cart) {
       cartMap.put(cart.getCustomerId(),cart);
    }

    @Override
    public void delete(Long customerId) {
      cartMap.remove(customerId);
    }

    @Override
    public Optional<Cart> findByCustomerId(Long customerId) {
        return Optional.ofNullable(cartMap.get(customerId));
    }


}
