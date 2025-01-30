package com.omarhammad.loans.controllers.dtos;

import com.omarhammad.loans.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loan", description = "Schema to hold Loan information")

public class LoanDTO {

    @NotEmpty(message = "Mobile NUmber can not be null or empty")
    @ValidPhoneNumber
    @Schema(description = "Mobile Number of the Customer", example = "+32465123456")
    private String mobileNumber;

    @NotEmpty(message = "Loan number can not be null or empy")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")
    @Schema(description = "The loan number for a customer", example = "937526506453")
    private String loanNumber;

    @NotEmpty(message = "loan type can not be null or empty")
    @Schema(description = "Type of the loan", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(description = "Total loan amount", example = "100000")
    private Long totalLoan;


    @PositiveOrZero(message = " The outstanding amount should be zero or greater")
    @Schema(description = "The outStanding amount against a loan", example = "99000")
    private Long outStandingAmount;


}
