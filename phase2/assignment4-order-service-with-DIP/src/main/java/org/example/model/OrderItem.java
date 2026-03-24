package org.example.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.UUID;


@Data
public class OrderItem {
    private final String id;
    private int quantity;
    private String name;
    private BigDecimal price;

    public OrderItem(int quantity, String name, BigDecimal price){
        this.id = UUID.randomUUID().toString().substring(0,5);
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }


}
