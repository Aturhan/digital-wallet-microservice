package com.abdullah.exception;

public class NotEnoughAmountToExpenseException extends Exception{
    public NotEnoughAmountToExpenseException(String message){
        super(message);
    }
}
