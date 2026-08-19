package com.example.springlab.domain.mapper;
import com.example.springlab.domain.dto.CustomerRequest;
import com.example.springlab.domain.dto.CustomerResponse;
import com.example.springlab.domain.entity.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer customer);
}
