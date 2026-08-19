package com.example.springlab.domain.mapper;

import com.example.springlab.domain.dto.OrderItemResponse;
import com.example.springlab.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponse toResponse(OrderItem orderItem);
}
