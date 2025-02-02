package com.omarhammad.cards.repositories.cardRepos;

import com.omarhammad.cards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {
}
