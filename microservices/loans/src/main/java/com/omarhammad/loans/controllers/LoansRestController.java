package com.omarhammad.loans.controllers;

import com.omarhammad.loans.controllers.dtos.ErrorResponseDTO;
import com.omarhammad.loans.controllers.dtos.LoanDTO;
import com.omarhammad.loans.controllers.dtos.RepaymentDTO;
import com.omarhammad.loans.controllers.dtos.ResponseDTO;
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
@RequestMapping(value = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Loans", description = "Loans Management APIs")
@AllArgsConstructor
@Validated
public class LoansRestController {


    private final ILoansService loansService;
    private final Logger logger = LoggerFactory.getLogger(LoansRestController.class);

    @Operation(summary = "Fetch Loan REST API", description = "REST API  to fetch loan for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = LoanDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam
                                                    @Schema(example = "+32465123456") String mobileNumber) {
        logger.info("mobile number : {}", mobileNumber);
        LoanDTO loanDTO = loansService.getLoan(mobileNumber);
        return ResponseEntity.ok(loanDTO);
    }


    @Operation(summary = "Create Loan REST API", description = "REST API  to create a new loan for a customer")
    @ApiResponses
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createLoan(@RequestBody @Valid LoanDTO loanDTO) {

        loansService.createLoan(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED, "Loan created successfully"));
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody @Valid LoanDTO loanDTO) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseDTO(HttpStatus.NO_CONTENT, "Loan updated Successfully"));
    }


    @PostMapping("/{loanId}/repayment")
    public ResponseEntity<ResponseDTO> loanRepayment(@RequestBody @Valid RepaymentDTO repaymentDTO) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseDTO(HttpStatus.NO_CONTENT,
                        " %d amount paid successfully".formatted(repaymentDTO.getAmount())));
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam @ValidPhoneNumber String mobileNumber) {

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "Loan deleted successfully"));
    }
}
