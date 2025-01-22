package com.omarhammad.accounts.controllers.dtos.accounts;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Schema(name = "Account", description = "Schema to hold Account information")

public class AccountsDTO {

    @Schema(description = "AccountNumber can not be null or empty")
    @NotEmpty(message = "account number must be provided!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "AccountType can not be null or empty", example = "[SAVINGS, BUSINESS, PERSONAL, FAMILY]")
    @NotEmpty(message = "account number must be provided! e.g: [SAVINGS, BUSINESS, PERSONAL, FAMILY]")
    private String accountType;

    @Schema(description = "Branch address can not be null or empty", example = "Antwerpen street 123, 2000 Antwerpen")
    @NotEmpty(message = "branch address must be provided!")
    private String branchAddress;

}
