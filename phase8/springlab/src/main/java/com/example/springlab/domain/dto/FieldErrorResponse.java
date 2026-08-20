package com.example.springlab.domain.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
