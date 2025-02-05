package com.omarhammad.cards.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.omarhammad.cards.exceptions.InvalidCardTypeException;
import com.omarhammad.cards.exceptions.InvalidTransactionTypeException;

import java.util.Arrays;

public enum TransactionType {
    WITHDRAW, DEPOSIT;


    @JsonCreator
    public static TransactionType fromString(String value) {
        for (TransactionType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new InvalidTransactionTypeException("Transaction Type should be " + Arrays.toString(values()));
    }

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
