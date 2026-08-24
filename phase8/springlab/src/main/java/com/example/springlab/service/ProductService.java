package com.example.springlab.service;

import com.example.springlab.dto.PageResponse;
import com.example.springlab.dto.ProductRequest;
import com.example.springlab.dto.ProductResponse;
import com.example.springlab.entity.Category;
import com.example.springlab.entity.Product;
import com.example.springlab.mapper.PageMapper;
import com.example.springlab.mapper.ProductMapper;
import com.example.springlab.exception.OutOfStockException;
import com.example.springlab.exception.ResourceNotFoundException;
import com.example.springlab.repository.CategoryRepository;
import com.example.springlab.repository.ProductRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;



    @Transactional
    public ProductResponse createProduct(ProductRequest request){
        Product product = productMapper.toEntity(request);
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND,request.categoryId())));
        product.setCategory(category);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    public ProductResponse findProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.PRODUCT_NOT_FOUND,id)));
        return productMapper.toResponse(product);
    }

    public PageResponse<ProductResponse> findAll(Pageable pageable){
        Page<Product> page= productRepository.findAll(pageable);
        return pageMapper.toPageResponse(page,productMapper::toResponse);
    }

    public PageResponse<ProductResponse> findByCategory(Long categoryId,Pageable pageable){
        Page<Product> page= productRepository.findByCategoryId(categoryId,pageable);
        return pageMapper.toPageResponse(page,productMapper::toResponse);
    }

    @Transactional
    public ProductResponse updateProduct(Long id,ProductRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.PRODUCT_NOT_FOUND,id)));
        productMapper.updateProduct(request,product);
        if(!(request.categoryId().equals(product.getCategory().getId()))){
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format(ErrorMessages.CATEGORY_NOT_FOUND,request.categoryId())));
            product.setCategory(category);
        }
        return productMapper.toResponse(product);
    }


    @Transactional
    public void deleteById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.PRODUCT_NOT_FOUND,id)));
        productRepository.delete(product);
    }

    public void reduceStock(Product product,int quantity){
        if(product.getStock()<quantity){
            throw new OutOfStockException(
                    String.format(ErrorMessages.PRODUCT_OUT_OF_STOCK, product.getName(), product.getStock()));
        }
        product.setStock(product.getStock()-quantity);
    }




}
