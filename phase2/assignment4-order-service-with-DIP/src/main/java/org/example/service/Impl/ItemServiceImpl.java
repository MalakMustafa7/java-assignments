package org.example.service.Impl;

import org.example.exception.NotFoundException;
import org.example.exception.OutOfStockException;
import org.example.model.OrderItem;
import org.example.repository.ItemRepository;
import org.example.service.ItemService;
import org.example.utility.ErrorMessages;

public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    @Override
    public boolean isInStock(String itemId, int requiredQuantity) {
        OrderItem item = findItemById(itemId);
        return item.getQuantity() > requiredQuantity;
    }

    @Override
    public void reduceStock(String itemId, int quantity) {
        OrderItem item = findItemById(itemId);
        if(!isInStock(itemId,quantity)){
            throw new OutOfStockException(ErrorMessages.OUT_OF_STOCK);
        }
       item.setQuantity(item.getQuantity()-quantity);
    }

    @Override
    public OrderItem findItemById(String itemId) {
       return  itemRepository.findById(itemId)
                .orElseThrow(()->new NotFoundException(ErrorMessages.ITEM_NOT_FOUND));
    }

    @Override
    public void addItem(OrderItem item) {
        if(item == null){
            throw new IllegalArgumentException("");
        }
        itemRepository.save(item);
    }
}
