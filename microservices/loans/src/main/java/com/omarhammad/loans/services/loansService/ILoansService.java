package com.omarhammad.loans.services.loansService;

import com.omarhammad.loans.controllers.dtos.LoanDTO;
import com.omarhammad.loans.controllers.dtos.RepaymentDTO;

public interface ILoansService {

    LoanDTO getLoan(String mobileNumber);

    void createLoan(LoanDTO loanDTO);

    void updateLoan(LoanDTO loanDTO);

    void loanRepayment(String loanId, RepaymentDTO repaymentDTO);

    void deleteLoan(String mobileNumber);


}
