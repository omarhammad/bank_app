package com.omarhammad.cards.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private String pinCode;

    private Long totalLimit;

    private Long availableAmount;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactions;



}
