package com.omarhammad.accounts.controllers.dtos.accounts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class AccountsDTO {

    @NotNull(message = "account number must be provided!")
    @NotBlank(message = "account number must be provided!")
    private Long accountNumber;

    @NotNull(message = "account number must be provided! e.g: [SAVINGS, BUSINESS, PERSONAL, FAMILY]")
    @NotBlank(message = "account number must be provided! e.g: [SAVINGS, BUSINESS, PERSONAL, FAMILY]")
    private String AccountType;
    @NotNull(message = "branch address must be provided!")
    @NotBlank(message = "branch address must be provided!")
    private String branchAddress;

}
