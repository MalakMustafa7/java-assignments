package com.example.springlab.mapper;

import com.example.springlab.dto.AuthorityRequest;
import com.example.springlab.dto.AuthorityResponse;
import com.example.springlab.entity.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    Authority toEntity(AuthorityRequest request);

    AuthorityResponse toResponse(Authority authority);
}
