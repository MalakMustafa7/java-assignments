package com.example.springlab.dto;

import java.util.Set;

public record RoleResponse(
        Long id,
        String name,
        Set<AuthorityResponse> authorities
) {
}
