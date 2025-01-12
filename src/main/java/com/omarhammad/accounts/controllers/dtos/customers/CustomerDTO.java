package com.omarhammad.accounts.controllers.dtos.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;

    private String email;

    @ValidPhoneNumber
    private String mobileNumber;


    private AccountsDTO account;
}
