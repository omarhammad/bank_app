package com.omarhammad.cards.utils.phoneNumberValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(value, null);

            return phoneNumberUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }
}