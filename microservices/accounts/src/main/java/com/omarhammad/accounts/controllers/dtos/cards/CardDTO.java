package com.omarhammad.accounts.controllers.dtos.cards;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Card", description = "Schema to hold customers card information")
public class CardDTO {
    @Schema(description = "The mobile number of a customer")

    private String mobileNumber;

    @Schema(description = "Card number of the card")

    private String cardNumber;

    @Schema(description = "The type of the card", example = "Debit")
    private String cardType;


    @Schema(description = "the limit the customer can withdraw from this card", example = "250")
    private Long totalLimit;

    @Schema(description = "the total Available amount for a card", example = "100000")
    private Long balance;
}
