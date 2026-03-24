package org.example.repository.impl;

import org.example.model.OrderItem;
import org.example.repository.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryItemRepository implements ItemRepository {
    private final Map<String, OrderItem> itemMap=new HashMap<>();

    @Override
    public void save(OrderItem item) {
        itemMap.put(item.getId(),item);
    }

    @Override
    public List<OrderItem> findAll() {
        return itemMap.values().stream().toList();
    }

    @Override
    public Optional<OrderItem> findById(String itemId) {
        return Optional.of(itemMap.get(itemId));
    }

    @Override
    public void deleteById(String itemId) {
        itemMap.remove(itemId);
    }
}
