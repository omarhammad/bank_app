package com.omarhammad.cards.controllers.dto;

import com.omarhammad.cards.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class DeleteRequestDTO {

    @Schema(description = "Card number of the card with 'luhn-algorithm' using https://www.dcode.fr/luhn-algorithm", example = "7992-7398-713")
    @NotEmpty(message = "Card number can't be empty or null")
    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
    private String cardNumber;


    @Schema(description = "the pin code of the card", example = "1231")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String pinCode;




}
