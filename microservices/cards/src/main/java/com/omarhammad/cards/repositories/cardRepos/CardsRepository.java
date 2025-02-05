package com.omarhammad.cards.repositories.cardRepos;

import com.omarhammad.cards.domain.Card;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {
    Optional<Card> findCardByMobileNumber(String mobileNumber);

    Optional<Card> findCardByCardNumber(String cardNumber);
}
