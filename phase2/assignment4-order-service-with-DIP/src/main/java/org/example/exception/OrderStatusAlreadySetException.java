package org.example.exception;

public class OrderStatusAlreadySetException extends RuntimeException{
    public OrderStatusAlreadySetException(String message) {
        super(message);
    }
}
