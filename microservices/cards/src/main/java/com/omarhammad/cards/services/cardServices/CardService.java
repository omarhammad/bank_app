package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.*;
import com.omarhammad.cards.domain.Card;
import com.omarhammad.cards.domain.CardType;
import com.omarhammad.cards.domain.Transaction;
import com.omarhammad.cards.domain.TransactionType;
import com.omarhammad.cards.exceptions.*;
import com.omarhammad.cards.repositories.cardRepos.CardsRepository;
import com.omarhammad.cards.repositories.transactioRepos.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CardService implements ICardService {

    private final TransactionRepository transactionRepository;
    private CardsRepository cardsRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public CardDTO getCard(String mobileNumber) {

        Card card = cardsRepository.findCardByMobileNumber(mobileNumber)
                .orElseThrow(() -> new EntityNotFoundException("Card with this mobile number %s not found".formatted(mobileNumber)));

        card.setPinCode(null);

        return modelMapper.map(card, CardDTO.class);
    }

    @Override
    public void createCard(CardDTO cardDTO) {

        cardsRepository.findCardByMobileNumber(cardDTO.getMobileNumber()).ifPresent((c) -> {
            throw new CardAlreadyExistsException("Card with this mobile number %s already exists".formatted(c.getMobileNumber()));
        });

        cardsRepository.findCardByCardNumber(cardDTO.getCardNumber()).ifPresent((c) -> {
            throw new CardAlreadyExistsException("Card with this card number %s already exists".formatted(c.getCardNumber()));
        });


        Card card = modelMapper.map(cardDTO, Card.class);

        card.setCardType(CardType.fromString(cardDTO.getCardType()));

        String encryptedPinCode = passwordEncoder.encode(card.getPinCode());
        card.setPinCode(encryptedPinCode);


        cardsRepository.save(card);
    }

    @Override
    public void updateCard(UpdateCardDTO updateCardDTO) {

        Card card = cardsRepository.findCardByCardNumber(updateCardDTO.getCardNumber())
                .orElseThrow(() -> new EntityNotFoundException("Card with this card number %s not found".formatted(updateCardDTO.getCardNumber())));


        if (!verifyPinCode(card.getPinCode(), updateCardDTO.getPinCode()))
            throw new InvalidPinCodeException("Invalid Pin Code! try again");

        card.setCardType(CardType.fromString(updateCardDTO.getCardType()));
        card.setTotalLimit(updateCardDTO.getTotalLimit());
        card.setMobileNumber(updateCardDTO.getMobileNumber());

        cardsRepository.save(card);

    }

    @Override
    public void changePinCode(String cardNumber, PinCodeChangeDTO pinCodeChangeDTO) {

        if (!cardNumber.equals(pinCodeChangeDTO.getCardNumber()))
            throw new CardNumberConflictException("Path and body card number should match");

        Card card = cardsRepository.findCardByCardNumber(pinCodeChangeDTO.getCardNumber())
                .orElseThrow(() -> new EntityNotFoundException("Card with this card number %s not found".formatted(pinCodeChangeDTO.getCardNumber())));


        if (!verifyPinCode(card.getPinCode(), pinCodeChangeDTO.getOldPinCode()))
            throw new InvalidPinCodeException("Invalid pin code! try again");

        if (pinCodeChangeDTO.getOldPinCode().equals(pinCodeChangeDTO.getNewPinCode()))
            throw new InvalidOldNewPinCodeMatchException("New Pin code should be different than old one");


        String encodedNewPinCode = passwordEncoder.encode(pinCodeChangeDTO.getNewPinCode());
        card.setPinCode(encodedNewPinCode);
        cardsRepository.save(card);
    }


    @Override
    public void deleteCard(DeleteRequestDTO deleteRequestDTO) {

        Card card = cardsRepository.findCardByCardNumber(deleteRequestDTO.getCardNumber())
                .orElseThrow(() -> new EntityNotFoundException("Card with this card number %s not found".formatted(deleteRequestDTO.getCardNumber())));

        if (!verifyPinCode(card.getPinCode(), deleteRequestDTO.getPinCode()))
            throw new InvalidPinCodeException("Invalid pin code! try again");

        cardsRepository.delete(card);

    }

    @Override
    @Transactional
    public void makeTransaction(String cardNumber, TransactionDTO transactionDTO) {

        Card card = cardsRepository.findCardByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Card with this card number %s not found".formatted(cardNumber)));

        if (!verifyPinCode(card.getPinCode(), transactionDTO.getPinCode()))
            throw new InvalidPinCodeException("Invalid pin code! try again");


        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.fromString(transactionDTO.getTransactionType()));
        transaction.setAmount(transactionDTO.getTransactionAmount());
        transaction.setCard(card);

        transactionProcess(transaction, card);

        transactionRepository.save(transaction);

    }


    private void transactionProcess(Transaction transaction, Card card) {

        Long balance = card.getBalance();
        if (balance == null) balance = 0L;

        Long transactionAmount = transaction.getAmount();

        switch (transaction.getTransactionType()) {
            case TransactionType.DEPOSIT -> card.setBalance(balance + transactionAmount);
            case TransactionType.WITHDRAW -> {
                if (balance < transactionAmount)
                    throw new InvalidWithdrawTransaction("Your Balance is not sufficient");

                card.setBalance(balance - transactionAmount);
            }
        }

    }


    private boolean verifyPinCode(String encodedPinCOde, String pinCode) {

        return passwordEncoder.matches(pinCode, encodedPinCOde);
    }
}
