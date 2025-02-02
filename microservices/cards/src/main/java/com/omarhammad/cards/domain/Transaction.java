package com.omarhammad.cards.domain;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    private Card card;


}
