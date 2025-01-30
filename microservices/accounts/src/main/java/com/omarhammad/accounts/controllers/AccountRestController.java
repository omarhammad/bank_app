package com.omarhammad.accounts.controllers;

import com.omarhammad.accounts.controllers.dtos.ErrorResponseDTO;
import com.omarhammad.accounts.controllers.dtos.ResponseDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDTO;
import com.omarhammad.accounts.services.accounts.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@PathVariable String mobileNumber) {

        CustomerDTO customerDTO = accountsService.fetchAccountDetails(mobileNumber);

        return ResponseEntity.ok(customerDTO);

    }

    @Operation(
            summary = "Update Account",
            description = "Update Account and Customer info."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@RequestBody @Valid CustomerDTO customerDTO) {

        accountsService.updateAccountDetails(customerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "Account updated successfully"));

    }


    @Operation(
            summary = "Delete Account",
            description = "Delete Account and Customer."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@PathVariable String mobileNumber) {

        accountsService.deleteAccountDetails(mobileNumber);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "Account deleted successfully"));

    }


}
