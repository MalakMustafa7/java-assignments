package com.example.springlab.controller;

import com.example.springlab.domain.dto.OrderRequest;
import com.example.springlab.domain.dto.OrderResponse;
import com.example.springlab.domain.dto.PageResponse;
import com.example.springlab.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(orderService.findById(id));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByCustomerId(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.ok()
                .body(orderService.findByCustomerId(id,pageable));
    }

    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> getAllOrders(Pageable pageable){
        return ResponseEntity.ok()
                .body(orderService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

