package com.omarhammad.accounts.controllers.dtos.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Customer name must be provided!")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email must be provided!")
    @Email(message = "email format is wrong! should be e.g example@mail.com")
    private String email;


    @NotEmpty(message = "mobile number must be provided!")
    @ValidPhoneNumber
    private String mobileNumber;


    private AccountsDTO account;
}
