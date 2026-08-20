package com.example.springlab.repository;

import com.example.springlab.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @EntityGraph(attributePaths = "category")
    Page<Product> findAll(Pageable pageable);


   @Query("""
           select p
           from Product p
           join fetch p.category
            where p.category.id = :categoryId
           """
   )
    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

}
