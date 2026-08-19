package com.example.springlab.domain.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        BigDecimal price,
        int quantity
) {
}
