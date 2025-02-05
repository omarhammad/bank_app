package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.*;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.stereotype.Repository;

public interface ICardService {

    CardDTO getCard(String mobileNumber);

    void createCard(CardDTO cardDTO);

    void updateCard(UpdateCardDTO cardDTO);

    void changePinCode(String cardNumber, @Valid PinCodeChangeDTO pinCodeChangeDTO);

    void deleteCard(DeleteRequestDTO deleteRequestDTO);


    void makeTransaction(String cardNumber, TransactionDTO transactionDTO);

}
