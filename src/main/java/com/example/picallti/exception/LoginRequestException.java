package com.example.picallti.exception;

public class LoginRequestException extends RuntimeException{
    public LoginRequestException(String message){
        super(message);
    }
}
