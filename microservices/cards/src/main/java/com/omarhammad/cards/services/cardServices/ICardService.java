package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.CardDTO;
import com.omarhammad.cards.controllers.dto.UpdateCardDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

public interface ICardService {

    CardDTO getCard(String mobileNumber);

    void createCard(CardDTO cardDTO);

    void updateCard(UpdateCardDTO cardDTO);

    void deleteCard(String mobileNumber);
}
