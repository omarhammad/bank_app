package com.omarhammad.accounts.services.accounts;

import com.omarhammad.accounts.controllers.dtos.accounts.AccountsDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import com.omarhammad.accounts.domain.Account;
import com.omarhammad.accounts.domain.AccountType;
import com.omarhammad.accounts.domain.Customer;
import com.omarhammad.accounts.exceptions.CustomerAlreadyExistsException;
import com.omarhammad.accounts.exceptions.InvalidAccountTypeException;
import com.omarhammad.accounts.repository.accounts.AccountsRepository;
import com.omarhammad.accounts.repository.customers.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        customerRepository.findCustomerByMobileNumber(customer.getMobileNumber())
                .ifPresent(c -> {
                    throw new CustomerAlreadyExistsException(
                            "Customer already registered with the given mobile number: " + c.getMobileNumber()
                    );
                });

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        Account newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Account account = accountsRepository.findAccountsByCustomer_Id(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account for customer %s not found!".formatted(customer.getName())));

        AccountsDTO accountsDTO = modelMapper.map(account, AccountsDTO.class);


        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setAccount(accountsDTO);


        return customerDTO;
    }

    @Override
    public void updateAccountDetails(CustomerDTO customerDTO) {

        AccountsDTO accountsDTO = customerDTO.getAccount();

        if (accountsDTO != null) {
            Account account = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(() -> new EntityNotFoundException("Account with Account Number %s not found".formatted(accountsDTO.getAccountNumber())));
            account.setBranchAddress(accountsDTO.getBranchAddress());
            try {
                account.setAccountType(AccountType.valueOf(accountsDTO.getAccountType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidAccountTypeException("Invalid account type: " + accountsDTO.getAccountType() + ". Provide one of these (" + Arrays.toString(AccountType.values()) + ")");
            }
            account.setUpdatedAt(LocalDateTime.now());
            account.setUpdatedBy("Anonymous");
            account = accountsRepository.save(account);

            Customer customer = customerRepository.findById(account.getCustomer().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Customer with phone number %s not found".formatted(customerDTO.getMobileNumber())));

            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobileNumber(customerDTO.getMobileNumber());
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("Anonymous");
            customerRepository.save(customer);
        }


    }

    @Override
    @Transactional
    public void deleteAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer with phone number %s not found".formatted(mobileNumber)));

        accountsRepository.deleteAccountByCustomer_Id(customer.getId());
        customerRepository.deleteById(customer.getId());
    }


    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomer(customer);

        long randomAccNumber = 1000000000L + new Random().nextLong(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountType.SAVINGS);
        newAccount.setBranchAddress("123 Main Street, New York");
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");


        return newAccount;
    }
}
