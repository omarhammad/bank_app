package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.CardDTO;
import com.omarhammad.cards.controllers.dto.DeleteRequestDTO;
import com.omarhammad.cards.controllers.dto.TransactionDTO;
import com.omarhammad.cards.controllers.dto.UpdateCardDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

public interface ICardService {

    CardDTO getCard(String mobileNumber);

    void createCard(CardDTO cardDTO);

    void updateCard(UpdateCardDTO cardDTO);

    void deleteCard(DeleteRequestDTO deleteRequestDTO);

    void makeTransaction(String cardNumber, TransactionDTO transactionDTO);
}
