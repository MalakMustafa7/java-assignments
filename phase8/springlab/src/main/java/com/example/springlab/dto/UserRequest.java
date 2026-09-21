package com.example.springlab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "{user.username.required}")
        String username,

        @NotBlank(message = "{user.password.required}")
        String password,

        @NotBlank(message = "{user.email.required}" )
        @Email(message = "{user.email.invalid}")
        String email,

        @NotNull(message = "{user.role.required}")
        Long roleId
) {
}
