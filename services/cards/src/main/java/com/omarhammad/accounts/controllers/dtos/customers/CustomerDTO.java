package com.omarhammad.accounts.controllers.dtos.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDTO {

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


    private AccountsDTO account;
}
