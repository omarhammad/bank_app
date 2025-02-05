package com.omarhammad.cards.exceptions;

public class InvalidTransactionTypeException extends RuntimeException {
    public InvalidTransactionTypeException(String s) {
        super(s);
    }
}
