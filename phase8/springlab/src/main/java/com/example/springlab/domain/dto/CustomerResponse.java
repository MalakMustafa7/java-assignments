package com.example.springlab.domain.dto;

import java.math.BigDecimal;

public record CustomerResponse(
        Long id,
        String name,
        BigDecimal balance
) {
}
