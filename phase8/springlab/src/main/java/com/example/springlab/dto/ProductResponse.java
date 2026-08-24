package com.example.springlab.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        int stock,
        BigDecimal price,
        String categoryName
) { }
