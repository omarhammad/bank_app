package com.omarhammad.accounts.controllers.dtos.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotNull(message = "Customer name must be provided!")
    @NotBlank(message = "Customer name must be provided!")
    private String name;

    @NotNull(message = "Email must be provided!")
    @NotBlank(message = "Email must be provided!")
    @Email(message = "email format is wrong! should be e.g example@mail.com")
    private String email;


    @NotBlank(message = "mobile number must be provided!")
    @NotNull(message = "mobile number must be provided!")
    @ValidPhoneNumber
    private String mobileNumber;


    private AccountsDTO account;
}
