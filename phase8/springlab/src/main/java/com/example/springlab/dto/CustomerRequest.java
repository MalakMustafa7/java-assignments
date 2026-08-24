package com.example.springlab.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CustomerRequest(
        @NotBlank(message = "{customer.name.required}")
        String name,

        @NotNull(message = "{customer.balance.required}")
        @PositiveOrZero(message = "{customer.balance.positive}")
        BigDecimal balance,

        @NotBlank(message = "{customer.phone.required}")
        String phoneNumber
) {
}
