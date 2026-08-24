package com.example.springlab.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        String status,
        String message,
        List<FieldErrorResponse> fieldErrors,
        LocalDateTime timestamp
) {
}
