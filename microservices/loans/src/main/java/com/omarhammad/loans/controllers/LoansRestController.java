package com.omarhammad.loans.controllers;

import com.omarhammad.loans.controllers.dtos.*;
import com.omarhammad.loans.services.loansService.ILoansService;
import com.omarhammad.loans.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Loans", description = "Loans Management APIs")
@RequestMapping(value = "/api/v1/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansRestController {


    private final ILoansService loansService;
    private final Logger logger = LoggerFactory.getLogger(LoansRestController.class);
    private final LoansContactInfoDTO loansContactInfoDTO;

    @Operation(summary = "Fetch Loan REST API", description = "REST API  to fetch loan for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = LoanDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam @ValidPhoneNumber @Schema(example = "+32465123456") String mobileNumber) {
        logger.info("mobile number : {}", mobileNumber);
        LoanDTO loanDTO = loansService.getLoan(mobileNumber);
        return ResponseEntity.ok(loanDTO);
    }


    @Operation(summary = "Create Loan REST API", description = "REST API  to create a new loan for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("")
    public ResponseEntity<ResponseDTO> createLoan(@RequestBody @Valid LoanDTO loanDTO) {

        loansService.createLoan(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED, "Loan created successfully"));
    }

    @Operation(summary = "Update Loan REST API", description = "REST API TO update a loan information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST"),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR")
    })
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody @Valid LoanDTO loanDTO) {

        loansService.updateLoan(loanDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, "Loan updated Successfully"));
    }

    @Operation(summary = "Loan Repayment REST API", description = "REST API for customers to pay the the loan")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/{loanId}/repayment")
    public ResponseEntity<ResponseDTO> loanRepayment(@RequestBody @Valid RepaymentDTO repaymentDTO, @PathVariable String loanId) {
        loansService.loanRepayment(loanId, repaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED,
                        " %d amount paid successfully".formatted(repaymentDTO.getAmount())));
    }

    @Operation(summary = "Delete loan REST API", description = "REST API to delete a customer's loan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam @ValidPhoneNumber String mobileNumber) {
        loansService.deleteLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "Loan deleted successfully"));
    }


    @Operation(
            summary = "Get Contact Info using @ConfigurationProperties",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(value = "/contact-info")
    public ResponseEntity<LoansContactInfoDTO> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDTO);
    }
}
