package com.omarhammad.accounts.controllers;

import com.omarhammad.accounts.controllers.dtos.ResponseDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import com.omarhammad.accounts.services.accounts.IAccountsService;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountRestController {

    private final IAccountsService accountsService;

    @PostMapping("")

    public ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid CustomerDTO customerDTO) {

        accountsService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.value(), "Account created successfully"));
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@PathVariable @ValidPhoneNumber String mobileNumber) {


        CustomerDTO customerDTO = accountsService.fetchAccountDetails(mobileNumber);


        return ResponseEntity.ok(customerDTO);

    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@RequestBody @Valid CustomerDTO customerDTO) {

        accountsService.updateAccountDetails(customerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Account updated successfully"));

    }


    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@PathVariable @ValidPhoneNumber String mobileNumber) {

        accountsService.deleteAccountDetails(mobileNumber);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Account deleted successfully"));

    }


}
