package com.example.springlab.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank()
        String name
) {}