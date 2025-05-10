package com.omarhammad.accounts.services.customers;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDetailsDTO;
import com.omarhammad.accounts.domain.Account;
import com.omarhammad.accounts.domain.Customer;
import com.omarhammad.accounts.exceptions.InvalidPhoneNumberException;
import com.omarhammad.accounts.repository.accounts.AccountsRepository;
import com.omarhammad.accounts.repository.customers.CustomerRepository;
import com.omarhammad.accounts.services.feignClients.cards.CardsFeignClient;
import com.omarhammad.accounts.services.feignClients.loans.LoansFeignClient;
import com.omarhammad.accounts.utils.phoneNumberValidator.PhoneNumberValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    private PhoneNumberValidator phoneNumberValidator;
    private ModelMapper modelMapper;

    @Override
    public CustomerDetailsDTO getCustomerDetails(String mobileNumber) {

        if (!phoneNumberValidator.isValid(mobileNumber, null))
            throw new InvalidPhoneNumberException("Invalid phone number format");


        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Account account = accountsRepository.findAccountsByCustomer_Id(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account for customer %s not found!".formatted(customer.getName())));

        AccountsDTO accountsDTO = modelMapper.map(account, AccountsDTO.class);


        CustomerDetailsDTO customerDetailsDTO = modelMapper.map(customer, CustomerDetailsDTO.class);
        customerDetailsDTO.setAccount(accountsDTO);
        customerDetailsDTO.setCardDTO(cardsFeignClient.getCardDetails(mobileNumber).getBody());
        customerDetailsDTO.setLoanDTO(loansFeignClient.fetchLoanDetails(mobileNumber).getBody());


        return customerDetailsDTO;
    }
}
