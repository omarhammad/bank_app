package com.omarhammad.cards.exceptions;

public class InvalidWithdrawTransaction extends RuntimeException {
    public InvalidWithdrawTransaction(String s) {
        super(s);
    }
}
