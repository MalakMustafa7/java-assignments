package com.example.springlab.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank()
         String name,
         @PositiveOrZero()
         int stock,
        @NotNull()
        @Positive()
        BigDecimal price,

        @NotNull()
         Long categoryId
) { }
