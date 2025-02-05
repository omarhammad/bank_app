package com.omarhammad.cards.controllers.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Schema(name = "Transaction", description = "Schema to hold Transaction information")
public class TransactionDTO {


    @Schema(description = "The card pin code", example = "1234")
    @Size(min = 4, max = 4, message = "Pin code should be 4 digits")
    private String pinCode;

    @Schema(description = "The amount against the available amount", example = "100")
    @Positive(message = "The Transaction Amount can't be empty or null")
    private Long transactionAmount;

    @Schema(description = "The transaction type", example = "[Withdraw, Deposit]")
    @NotEmpty(message = "Transaction type can't be null or empty")
    private String transactionType;


}
