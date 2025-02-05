package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.*;
import jakarta.validation.Valid;

public interface ICardService {

    CardDTO getCard(String cardNumber,String pinCode);

    void createCard(CreateCardDTO createCardDTO);

    void updateCard(UpdateCardDTO cardDTO);

    void changePinCode(String cardNumber, @Valid PinCodeChangeDTO pinCodeChangeDTO);

    void deleteCard(DeleteRequestDTO deleteRequestDTO);


    void makeTransaction(String cardNumber, TransactionDTO transactionDTO);

}
