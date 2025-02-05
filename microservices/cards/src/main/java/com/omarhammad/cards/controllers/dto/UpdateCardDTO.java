package com.omarhammad.cards.controllers.dto;

import com.omarhammad.cards.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Schema(name = "Update Card", description = "Schema to hold customer update card information")
public class UpdateCardDTO {


    @Schema(description = "Card number of the card", example = "2042-8722-738")
    @NotEmpty(message = "Card number can't be empty or null")
    @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
    private String cardNumber;

    @Schema(description = "the pin code of the card", example = "1231")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String pinCode;

    @Schema(description = "The mobile number of a customer", example = "+32465358795")
    @ValidPhoneNumber
    @NotEmpty(message = "mobile number can't be null or empty")
    private String mobileNumber;

    @Schema(description = "The type of the card", example = "Credit")
    @NotEmpty(message = "Card type can't be empty or null")
    private String cardType;

    @Schema(description = "the limit the customer can withdraw from this card", example = "100")
    @Positive(message = "Total limit should be greater than zero")
    private Long totalLimit;


}
