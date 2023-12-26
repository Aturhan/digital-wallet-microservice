package com.abdullah.exception;

public class MissingAuthorizationHeaderException extends Exception{
    public MissingAuthorizationHeaderException(String message){
        super(message);
    }
}
