package com.example.springlab.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "{category.name.required}")
        String name
) {}