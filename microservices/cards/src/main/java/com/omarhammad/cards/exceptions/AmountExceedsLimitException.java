package com.omarhammad.cards.exceptions;

public class AmountExceedsLimitException extends RuntimeException {
    public AmountExceedsLimitException(String s) {
        super(s);
    }
}
