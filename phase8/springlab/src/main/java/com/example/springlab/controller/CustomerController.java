package com.example.springlab.controller;

import com.example.springlab.domain.dto.CustomerRequest;
import com.example.springlab.domain.dto.CustomerResponse;
import com.example.springlab.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(customerService.CreateCustomer(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(customerService.findById(id));
    }
}
