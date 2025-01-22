package com.omarhammad.accounts.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType AccountType;

    private String branchAddress;

    @OneToOne
    private Customer customer;


}