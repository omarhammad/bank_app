package com.omarhammad.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAccountTypeException extends RuntimeException {
    public InvalidAccountTypeException(String s) {
        super(s);
    }
}
