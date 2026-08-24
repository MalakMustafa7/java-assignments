package com.example.springlab.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotBlank(message = "{order.name.required}")
        String name,

        @NotNull(message = "{order.customerId.required}")
        Long customerId,

        @Valid
        @NotEmpty(message = "{order.items.required}")
        List<OrderItemRequest> orderItems
) {
}
