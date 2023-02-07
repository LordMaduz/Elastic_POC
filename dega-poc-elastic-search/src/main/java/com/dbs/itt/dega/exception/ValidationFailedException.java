package com.dbs.itt.dega.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException(String message){
        super(message);
    }
}
