package com.example.springlab.mapper;

import com.example.springlab.dto.CategoryRequest;
import com.example.springlab.dto.CategoryResponse;
import com.example.springlab.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest requestDto);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toAllResponse(List<Category> categories);
}
