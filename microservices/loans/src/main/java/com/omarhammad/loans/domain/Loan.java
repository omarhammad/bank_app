package com.omarhammad.loans.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Long totalLoan;

    private Long outstandingAmount;

    @OneToMany(mappedBy = "loan")
    private List<Repayment> repayments;


}
