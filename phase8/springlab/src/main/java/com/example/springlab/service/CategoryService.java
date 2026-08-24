package com.example.springlab.service;

import com.example.springlab.dto.CategoryRequest;
import com.example.springlab.dto.CategoryResponse;
import com.example.springlab.entity.Category;
import com.example.springlab.mapper.CategoryMapper;
import com.example.springlab.exception.ResourceNotFoundException;
import com.example.springlab.repository.CategoryRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryResponse createCategory(CategoryRequest requestDto){
        Category category = categoryMapper.toEntity(requestDto);
        Category savedCategory  =categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory );
    }

    public List<CategoryResponse> findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toAllResponse(categories);
    }

    public CategoryResponse findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND,id)));
        return categoryMapper.toResponse(category);

    }


}
