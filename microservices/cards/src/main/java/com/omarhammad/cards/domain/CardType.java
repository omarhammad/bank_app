package com.omarhammad.cards.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.omarhammad.cards.exceptions.InvalidCardTypeException;

import java.util.Arrays;

public enum CardType {
    CREDIT, DEBIT;


    @JsonCreator
    public static CardType fromString(String value) {
        for (CardType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new InvalidCardTypeException("Card Type should be "+ Arrays.toString(values()));
    }

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
