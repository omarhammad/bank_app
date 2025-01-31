package com.omarhammad.loans.services.loansService;

import com.omarhammad.loans.controllers.dtos.LoanDTO;
import com.omarhammad.loans.controllers.dtos.RepaymentDTO;
import com.omarhammad.loans.domain.Loan;
import com.omarhammad.loans.exceptions.InvalidMobileNumberException;
import com.omarhammad.loans.exceptions.LoanAlreadyExistsException;
import com.omarhammad.loans.repositories.loanRepo.LoanRepository;
import com.omarhammad.loans.utils.phoneNumberValidator.PhoneNumberValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansService implements ILoansService {

    private LoanRepository loanRepository;
    private ModelMapper modelMapper;
    private PhoneNumberValidator phoneNumberValidator;

    @Override
    public LoanDTO getLoan(String mobileNumber) {

        if (!phoneNumberValidator.isValid(mobileNumber, null))
            throw new InvalidMobileNumberException("Invalid mobile number");


        Loan loan = loanRepository.findLoanByMobileNumber(mobileNumber)
                .orElseThrow(() -> (new EntityNotFoundException("Loan with %s not found".formatted(mobileNumber))));

        return modelMapper.map(loan, LoanDTO.class);
    }

    @Override
    public void createLoan(LoanDTO loanDTO) {
        Loan loan = modelMapper.map(loanDTO, Loan.class);
        loanRepository.findLoanByMobileNumber(loan.getMobileNumber())
                .ifPresent((l) -> {
                    throw new LoanAlreadyExistsException(("Loan Already Exists for " +
                            "customer with mobile number %s").formatted(l.getMobileNumber()));
                });

        loanRepository.findLoanByLoanNumber(loan.getLoanNumber())
                .ifPresent((l) -> {
                    throw new LoanAlreadyExistsException(("Loan Already Exists with " +
                            "loan number %s").formatted(l.getLoanNumber()));
                });

        loanRepository.save(loan);

    }

    @Override
    public void updateLoan(LoanDTO loanDTO) {
        Loan loan = modelMapper.map(loanDTO, Loan.class);

        Long loanId = loanRepository.findLoanByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(() ->
                new EntityNotFoundException("Loan with %s not found!".formatted(loanDTO.getLoanNumber()))).getId();

        loan.setId(loanId);
        loanRepository.save(loan);

    }

    @Override
    public void loanRepayment(Long loanId, RepaymentDTO repaymentDTO) {

    }

    @Override
    public void deleteLoan(String mobileNumber) {
        if (phoneNumberValidator.isValid(mobileNumber, null))
            throw new InvalidMobileNumberException("Invalid mobile number");

    }
}
