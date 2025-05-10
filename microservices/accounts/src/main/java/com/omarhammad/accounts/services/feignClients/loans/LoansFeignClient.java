package com.omarhammad.accounts.services.feignClients.loans;

import com.omarhammad.accounts.controllers.dtos.loans.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/v1/loans" , consumes = "application/json" )
    ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam String mobileNumber);
}
