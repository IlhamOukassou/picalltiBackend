package com.example.picallti.exception;

public class FavorisNotFoundException extends RuntimeException {
    public FavorisNotFoundException(String message){
        super(message);
    }
}
