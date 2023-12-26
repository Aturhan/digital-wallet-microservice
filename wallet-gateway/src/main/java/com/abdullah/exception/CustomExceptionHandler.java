package com.abdullah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleInvalidTokenEx(InvalidTokenException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message: ", exception.getMessage());
        errorMap.put("Status: ", HttpStatus.BAD_REQUEST.toString());
        return errorMap;
    }

    @ExceptionHandler(MissingAuthorizationHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMissingAuthHeaderEx(MissingAuthorizationHeaderException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message: ", exception.getMessage());
        errorMap.put("Status: ", HttpStatus.BAD_REQUEST.toString());
        return errorMap;
    }

}
