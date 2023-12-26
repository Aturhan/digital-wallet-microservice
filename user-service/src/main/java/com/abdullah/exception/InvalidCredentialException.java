package com.abdullah.exception;

import org.apache.http.auth.InvalidCredentialsException;

public class InvalidCredentialException extends InvalidCredentialsException {
   public InvalidCredentialException (String message){
       super(message);
   }
}
