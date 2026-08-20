package com.example.springlab.controller;

import com.example.springlab.domain.dto.ErrorResponse;
import com.example.springlab.domain.dto.FieldErrorResponse;
import com.example.springlab.domain.dto.ValidationErrorResponse;
import com.example.springlab.exception.InsufficientBalanceException;
import com.example.springlab.exception.OutOfStockException;
import com.example.springlab.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldErrorResponse> errors= ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->new FieldErrorResponse(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList();

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
               "Validation failed",
                errors,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                String.valueOf(HttpStatus.CONFLICT.value()),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleMInsufficientBalanceException(InsufficientBalanceException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                String.valueOf(HttpStatus.CONFLICT.value()),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
