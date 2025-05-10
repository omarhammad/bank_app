package com.omarhammad.accounts.services.customers;

import com.omarhammad.accounts.controllers.dtos.customers.CustomerDetailsDTO;

public interface ICustomersService {

    CustomerDetailsDTO getCustomerDetails(String mobileNumber);

}
