package com.example.springlab.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorityRequest(
        @NotBlank(message = "{authority.name.required}")
        String name
) {
}
