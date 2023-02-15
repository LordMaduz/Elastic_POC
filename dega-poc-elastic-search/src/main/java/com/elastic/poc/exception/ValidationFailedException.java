package com.elastic.poc.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException(String message){
        super(message);
    }
}
