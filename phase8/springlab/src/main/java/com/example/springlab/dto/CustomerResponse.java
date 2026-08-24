package com.example.springlab.dto;

import java.math.BigDecimal;

public record CustomerResponse(
        Long id,
        String name,
        BigDecimal balance,
        String phoneNumber
) {
}
