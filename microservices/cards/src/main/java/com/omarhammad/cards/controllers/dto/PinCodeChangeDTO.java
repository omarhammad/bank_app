package com.omarhammad.cards.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Schema(name = "Pin Code Change ", description = "Schema to hold the pin code change information")
public class PinCodeChangeDTO {


    @Schema(description = "Card number of the card with 'luhn-algorithm' using https://www.dcode.fr/luhn-algorithm", example = "7992-7398-713")
    @NotEmpty(message = "Card number can't be empty or null")
    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
    private String cardNumber;


    @Schema(description = "the old pin code of the card", example = "1234")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String oldPinCode;

    @Schema(description = "the new pin code of the card", example = "1234")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String newPinCode;

}
