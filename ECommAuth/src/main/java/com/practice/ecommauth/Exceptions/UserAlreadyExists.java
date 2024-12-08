package com.practice.ecommauth.Exceptions;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String message)
    {
        super(message);
    }
}
