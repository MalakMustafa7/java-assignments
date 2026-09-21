package com.example.springlab.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        boolean enabled,
        String role
) {
}
