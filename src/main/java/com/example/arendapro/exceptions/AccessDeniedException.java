package com.example.arendapro.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AccessDeniedException extends Exception{

    public AccessDeniedException(String message){
        super(message);
    }
}
