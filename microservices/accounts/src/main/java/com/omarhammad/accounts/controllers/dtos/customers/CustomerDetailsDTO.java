package com.omarhammad.accounts.controllers.dtos.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.controllers.dtos.cards.CardDTO;
import com.omarhammad.accounts.controllers.dtos.loans.LoanDTO;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer, Account, Cards and Loans information")
public class CustomerDetailsDTO {
    @Schema(description = "Name of the customer", example = "Omar Hammad")
    @NotEmpty(message = "Customer name must be provided!")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of the customer", example = "omar@gmail.com")
    @NotEmpty(message = "Email must be provided!")
    @Email(message = "email format is wrong! should be e.g example@mail.com")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "+32465123456")
    @NotEmpty(message = "mobile number must be provided!")
    @ValidPhoneNumber
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDTO account;

    @Schema(
            description = "Card details of the Customer"
    )
    private CardDTO cardDTO;

    @Schema(
            description = "Loans details of the Customer"
    )
    private LoanDTO loanDTO;
}

