package com.example.springlab.repository;
import com.example.springlab.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @EntityGraph(attributePaths ={ "orderItems","orderItems.product"})
    Page<Order> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "orderItems.product")
    Optional<Order> findById(Long orderId);

    @EntityGraph(attributePaths ={ "orderItems","orderItems.product"})
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

}
