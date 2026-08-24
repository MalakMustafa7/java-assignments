package com.example.springlab.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        String name,
        BigDecimal totalPrice,
        String status,
        List<OrderItemResponse> orderItems
) {
}
