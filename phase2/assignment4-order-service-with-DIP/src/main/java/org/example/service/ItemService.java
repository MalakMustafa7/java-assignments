package org.example.service;

import org.example.model.OrderItem;

public interface ItemService {
    boolean isInStock(String itemId,int requiredQuantity);
    void reduceStock(String itemId, int quantity);
    OrderItem findItemById(String itemId);
    void addItem(OrderItem item);
}
