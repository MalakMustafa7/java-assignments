package com.example.springlab.controller;

import com.example.springlab.domain.dto.PageResponse;
import com.example.springlab.domain.dto.ProductRequest;
import com.example.springlab.domain.dto.ProductResponse;
import com.example.springlab.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.findProductById(id));
    }
    @GetMapping
    public  ResponseEntity<PageResponse<ProductResponse>> getAllProducts(Pageable pageable){
        return ResponseEntity.ok().body(productService.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.CreateProduct(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody ProductRequest request){
        return ResponseEntity.ok().body(productService.updateProduct(id,request));

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<PageResponse<ProductResponse>> getProductByCategory(@PathVariable Long id,
                                                                      Pageable pageable){
        return ResponseEntity.ok().body(productService.findByCategory(id,pageable));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}