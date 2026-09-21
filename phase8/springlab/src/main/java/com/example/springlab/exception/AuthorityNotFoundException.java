package com.example.springlab.exception;

public class AuthorityNotFoundException extends RuntimeException{
    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
