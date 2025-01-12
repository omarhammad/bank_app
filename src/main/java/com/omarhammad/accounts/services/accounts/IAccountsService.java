package com.omarhammad.accounts.services.accounts;

import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;

public interface IAccountsService {

    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    void updateAccountDetails(CustomerDTO customerDTO);

}
