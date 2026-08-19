package com.example.springlab.domain.mapper;

import com.example.springlab.domain.dto.OrderRequest;
import com.example.springlab.domain.dto.OrderResponse;
import com.example.springlab.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = OrderItemMapper.class)
public interface OrderMapper {

    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order order);
}
