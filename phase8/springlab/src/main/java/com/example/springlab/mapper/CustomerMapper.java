package com.example.springlab.mapper;
import com.example.springlab.dto.CustomerRequest;
import com.example.springlab.dto.CustomerResponse;
import com.example.springlab.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer customer);
}
