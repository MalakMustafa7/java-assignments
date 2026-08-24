package com.example.springlab.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        BigDecimal price,
        int quantity
) {
}
