package com.example.springlab.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CustomerRequest(
        @NotBlank()
        String name,
        @NotNull()
        @Positive
        BigDecimal balance
) {
}
