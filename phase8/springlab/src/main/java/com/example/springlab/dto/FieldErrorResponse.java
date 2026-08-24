package com.example.springlab.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
