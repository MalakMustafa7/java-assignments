package com.example.springlab.service;

import com.example.springlab.dto.CustomerRequest;
import com.example.springlab.dto.CustomerResponse;
import com.example.springlab.entity.Customer;
import com.example.springlab.mapper.CustomerMapper;
import com.example.springlab.exception.InsufficientBalanceException;
import com.example.springlab.exception.ResourceNotFoundException;
import com.example.springlab.repository.CustomerRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerResponse createCustomer(CustomerRequest request){
        Customer customer = customerMapper.toEntity(request);
       Customer saved= customerRepository.save(customer);
        return customerMapper.toResponse(saved);
    }

    public CustomerResponse findById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessages.CUSTOMER_NOT_FOUND,id)));
        return customerMapper.toResponse(customer);
    }

    public void deductBalance(Customer customer, BigDecimal amount){
        if(customer.getBalance().compareTo(amount)<0){
            throw new InsufficientBalanceException(String.format(ErrorMessages.CUSTOMER_INSUFFICIENT_BALANCE,customer.getName()));
        }
        customer.setBalance(customer.getBalance().subtract(amount));
    }


}
