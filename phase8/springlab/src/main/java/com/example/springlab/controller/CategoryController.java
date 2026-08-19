package com.example.springlab.controller;

import com.example.springlab.domain.dto.CategoryRequest;
import com.example.springlab.domain.dto.CategoryResponse;
import com.example.springlab.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>>getAllCategories(){
       List<CategoryResponse> allCategories= categoryService.findAllCategories();
       return ResponseEntity.ok().body(allCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
        CategoryResponse categoryResponseDto=
                categoryService.findById(id);
        return ResponseEntity.ok().body(categoryResponseDto);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest requestDto){
        CategoryResponse responseDto = categoryService.createCategory(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
