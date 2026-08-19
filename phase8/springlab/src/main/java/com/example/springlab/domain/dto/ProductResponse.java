package com.example.springlab.domain.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        int stock,
        BigDecimal price,
        String categoryName
) { }
