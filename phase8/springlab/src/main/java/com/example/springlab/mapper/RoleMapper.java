package com.example.springlab.mapper;

import com.example.springlab.dto.RoleResponse;
import com.example.springlab.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toResponse(Role role);
}
