package com.example.springlab.mapper;

import com.example.springlab.dto.OrderItemResponse;
import com.example.springlab.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponse toResponse(OrderItem orderItem);
}
