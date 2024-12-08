package com.practice.ecommauth.Exceptions;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message)
    {
        super(message);
    }
}
