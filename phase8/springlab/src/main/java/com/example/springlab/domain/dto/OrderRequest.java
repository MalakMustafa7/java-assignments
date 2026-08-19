package com.example.springlab.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotBlank()
        String name,

        @NotNull()
        Long customerId,

        @NotEmpty()
        List<OrderItemRequest> orderItems
) {
}
