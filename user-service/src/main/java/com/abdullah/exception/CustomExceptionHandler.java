package com.abdullah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> UserNotFoundEx(UserNotFoundException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message: ", e.getMessage());
        errorMap.put("Status: ", HttpStatus.NOT_FOUND.toString());
        return errorMap;
    }
    @ExceptionHandler(KafkaPublisherException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, String> KafkaException(KafkaPublisherException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message: ", e.getMessage());
        errorMap.put("Status: ", HttpStatus.SERVICE_UNAVAILABLE.toString());
        return errorMap;
    }
    @ExceptionHandler(InvalidCredentialException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> InvalidCredentialsException(InvalidCredentialException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message : ", exception.getMessage());
        errorMap.put("Status :",HttpStatus.UNAUTHORIZED.toString());
        return errorMap;
    }
}