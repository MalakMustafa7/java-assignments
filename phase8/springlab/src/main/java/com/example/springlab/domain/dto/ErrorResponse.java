package com.example.springlab.domain.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String status,
        String message,
        LocalDateTime timestamp
) {
}
