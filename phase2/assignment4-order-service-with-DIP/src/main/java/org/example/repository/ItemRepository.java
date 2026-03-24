package org.example.repository;

import org.example.model.OrderItem;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    void save(OrderItem item);
    List<OrderItem> findAll();
    Optional<OrderItem> findById(String itemId);

    void deleteById(String itemId);
}
