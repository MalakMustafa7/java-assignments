package com.example.springlab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record RoleRequest(

        @NotBlank(message = "{role.name.required}")
        String name,

        @NotEmpty(message = "{role.authorities.required}")
        Set<Long> authorityIds
) {
}
