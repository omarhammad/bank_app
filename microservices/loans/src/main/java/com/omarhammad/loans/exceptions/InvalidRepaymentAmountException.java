package com.omarhammad.loans.exceptions;

public class InvalidRepaymentAmountException extends RuntimeException {
    public InvalidRepaymentAmountException(String message) {
        super(message);
    }
}
