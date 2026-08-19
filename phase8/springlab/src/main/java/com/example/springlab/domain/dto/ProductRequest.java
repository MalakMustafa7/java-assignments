package com.example.springlab.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "{product.name.required}")
        String name,

        @PositiveOrZero(message = "{product.stock.positive}")
        int stock,

        @NotNull(message = "{product.price.required}")
        @Positive(message = "{product.price.positive}")
        BigDecimal price,

        @NotNull(message = "{product.category.required}")
        Long categoryId
) { }
