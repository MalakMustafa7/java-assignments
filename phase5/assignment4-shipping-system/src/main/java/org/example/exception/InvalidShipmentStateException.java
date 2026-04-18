package org.example.exception;

public class InvalidShipmentStateException extends RuntimeException{
    public InvalidShipmentStateException(String message) {
        super(message);
    }
}
