package com.omarhammad.accounts.services.customers;

import com.omarhammad.accounts.repository.customers.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
