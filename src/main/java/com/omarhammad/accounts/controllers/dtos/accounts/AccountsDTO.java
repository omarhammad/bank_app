package com.omarhammad.accounts.controllers.dtos.accounts;

import com.omarhammad.accounts.domain.AccountType;
import lombok.*;

@Data
public class AccountsDTO {

    private Long accountNumber;

    private String AccountType;

    private String branchAddress;

}
