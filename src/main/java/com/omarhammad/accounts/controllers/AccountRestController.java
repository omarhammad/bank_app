package com.omarhammad.accounts.controllers;

import com.omarhammad.accounts.controllers.dtos.ResponseDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import com.omarhammad.accounts.services.accounts.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Accounts", description = "Accounts Management APIs")
@AllArgsConstructor
@Validated
public class AccountRestController {

    private final IAccountsService accountsService;

    @Operation(
            summary = "Create New Account",
            description = "Create a new account using customer info only while account info internally created."
    )
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @PostMapping("")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid CustomerDTO customerDTO) {

        accountsService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.value(), "Account created successfully"));
    }

    @Operation(
            summary = "Fetch Account",
            description = "Fetch account and it's customer info by mobile number."
    )
    @GetMapping("/{mobileNumber}")
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@PathVariable String mobileNumber) {

        CustomerDTO customerDTO = accountsService.fetchAccountDetails(mobileNumber);

        return ResponseEntity.ok(customerDTO);

    }

    @Operation(
            summary = "Update Account",
            description = "Update Account and Customer info."
    )
    @ApiResponse(responseCode = "204", description = "HTTP Status NO_CONTENT")
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@RequestBody @Valid CustomerDTO customerDTO) {

        accountsService.updateAccountDetails(customerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Account updated successfully"));

    }


    @Operation(
            summary = "Delete Account",
            description = "Delete Account and Customer."
    )
    @ApiResponse(responseCode = "204", description = "HTTP Status NO_CONTENT")
    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@PathVariable String mobileNumber) {

        accountsService.deleteAccountDetails(mobileNumber);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Account deleted successfully"));

    }


}
