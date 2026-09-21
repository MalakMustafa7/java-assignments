package com.example.springlab.mapper;

import com.example.springlab.dto.UserRequest;
import com.example.springlab.dto.UserResponse;
import com.example.springlab.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    @Mapping(source = "role.name" , target = "role")
    UserResponse toResponse(User user);
}
