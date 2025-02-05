package com.omarhammad.cards.controllers.dto;

import com.omarhammad.cards.domain.CardType;
import com.omarhammad.cards.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Schema(name = "Card", description = "Schema to hold customers card information")
public class CardDTO {


    @Schema(description = "The mobile number of a customer", example = "+32465123456")
    @ValidPhoneNumber
    @NotEmpty(message = "mobile number can't be null or empty")
    private String mobileNumber;

    @Schema(description = "Card number of the card with 'luhn-algorithm' using https://www.dcode.fr/luhn-algorithm", example = "7992-7398-713")
    @NotEmpty(message = "Card number can't be empty or null")
    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
    private String cardNumber;

    @Schema(description = "The type of the card", example = "Debit")
    @NotEmpty(message = "Card type can't be empty or null")
    private String cardType;

    @Schema(description = "the pin code of the card", example = "1234")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String pinCode;

    @Schema(description = "the limit the customer can withdraw from this card", example = "250")
    @Positive(message = "Total limit should be greater than zero")
    private Long totalLimit;



}
