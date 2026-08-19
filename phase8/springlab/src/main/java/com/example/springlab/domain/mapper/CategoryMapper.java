package com.example.springlab.domain.mapper;

import com.example.springlab.domain.dto.CategoryRequest;
import com.example.springlab.domain.dto.CategoryResponse;
import com.example.springlab.domain.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest requestDto);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toAllResponse(List<Category> categories);
}
