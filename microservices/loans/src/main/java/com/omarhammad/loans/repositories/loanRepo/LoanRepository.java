package com.omarhammad.loans.repositories.loanRepo;

import com.omarhammad.loans.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findLoanByMobileNumber(String mobileNumber);
    Optional<Loan> findLoanByLoanNumber(Long loanNumber);
}
