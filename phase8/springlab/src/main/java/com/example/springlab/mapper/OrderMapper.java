package com.example.springlab.mapper;

import com.example.springlab.dto.OrderRequest;
import com.example.springlab.dto.OrderResponse;
import com.example.springlab.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = OrderItemMapper.class)
public interface OrderMapper {

    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order order);
}
