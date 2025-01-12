package com.omarhammad.accounts.services.accounts;

import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import jakarta.validation.Valid;

public interface IAccountsService {

    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    void updateAccountDetails(CustomerDTO customerDTO);

    void deleteAccountDetails(String mobileNumber);
}
