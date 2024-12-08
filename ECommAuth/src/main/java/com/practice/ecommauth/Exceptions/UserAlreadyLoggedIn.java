package com.practice.ecommauth.Exceptions;

public class UserAlreadyLoggedIn extends RuntimeException{

    public UserAlreadyLoggedIn(String message)
    {
        super(message);
    }
}
