package com.omarhammad.accounts.utils.phoneNumberValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.omarhammad.accounts.exceptions.InvalidPhoneNumberException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(value, null);

            if (!phoneNumberUtil.isValidNumber(phoneNumber)) {
                throw new InvalidPhoneNumberException("Invalid phone number format");
            }
            return true;
        } catch (NumberParseException e) {
            throw new InvalidPhoneNumberException("Invalid phone number format");
        }
    }
}