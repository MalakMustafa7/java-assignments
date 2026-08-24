package com.example.springlab.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String status,
        String message,
        LocalDateTime timestamp
) {
}
