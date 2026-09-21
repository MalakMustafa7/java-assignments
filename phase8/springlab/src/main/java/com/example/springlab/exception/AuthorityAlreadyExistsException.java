package com.example.springlab.exception;

public class AuthorityAlreadyExistsException extends RuntimeException{
    public AuthorityAlreadyExistsException(String message) {
        super(message);
    }
}
