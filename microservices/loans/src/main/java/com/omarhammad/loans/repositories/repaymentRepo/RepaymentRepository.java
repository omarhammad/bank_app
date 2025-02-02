package com.omarhammad.loans.repositories.repaymentRepo;

import com.omarhammad.loans.domain.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
}
