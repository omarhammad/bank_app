package com.omarhammad.accounts.controllers.dtos.accounts;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class AccountsDTO {

    @NotEmpty(message = "account number must be provided!")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "account number must be provided! e.g: [SAVINGS, BUSINESS, PERSONAL, FAMILY]")
    private String AccountType;
    @NotEmpty(message = "branch address must be provided!")
    private String branchAddress;

}
