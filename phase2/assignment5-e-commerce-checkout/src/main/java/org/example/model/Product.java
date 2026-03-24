package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
public class Product {
    private final Long productId;
    String name;
    BigDecimal price;
    int quantity;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
