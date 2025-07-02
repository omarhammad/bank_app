package com.omarhammad.accounts.controllers;

import com.omarhammad.accounts.controllers.dtos.ErrorResponseDTO;
import com.omarhammad.accounts.controllers.dtos.customers.CustomerDetailsDTO;
import com.omarhammad.accounts.services.customers.CustomerService;
import com.omarhammad.accounts.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Customer", description = "Customer Management APIs")
@Validated
public class CustomerRestController {


    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "Fetch customer details by mobile number."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@PathVariable
                                                                   @ValidPhoneNumber
                                                                   String mobileNumber) {
        CustomerDetailsDTO customerDetailsDTO = customerService.getCustomerDetails(mobileNumber);

        return ResponseEntity.ok(customerDetailsDTO);

    }
}
