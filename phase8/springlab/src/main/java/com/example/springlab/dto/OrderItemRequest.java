package com.example.springlab.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull(message = "{orderItem.productId.required}")
        Long productId,

        @Positive(message = "{orderItem.quantity.positive}")
        int quantity
) {
}
