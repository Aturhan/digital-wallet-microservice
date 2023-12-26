package com.abdullah.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MyEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> myEntityNotFoundEx(MyEntityNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message:",exception.getMessage());
        errorMap.put("Status:",HttpStatus.NOT_FOUND.toString());
        return errorMap;
    }
    @ExceptionHandler(NotEnoughAmountToExpenseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> NotEnoughAmountToExpenseEx(NotEnoughAmountToExpenseException e){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Message:", e.getMessage());
        errorMap.put("Status:",HttpStatus.BAD_REQUEST.toString());
        return errorMap;
    }
}
