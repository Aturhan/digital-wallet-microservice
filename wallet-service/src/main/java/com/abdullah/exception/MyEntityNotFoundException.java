package com.abdullah.exception;

import jakarta.persistence.EntityNotFoundException;

public class MyEntityNotFoundException extends EntityNotFoundException {
    public MyEntityNotFoundException(String message){
        super(message);
    }
}
