package com.example.springlab.domain.mapper;

import com.example.springlab.domain.dto.ProductRequest;
import com.example.springlab.domain.dto.ProductResponse;
import com.example.springlab.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest productDto);

    @Mapping(source = "category.name",target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateProduct(ProductRequest request,@MappingTarget Product product);
}
