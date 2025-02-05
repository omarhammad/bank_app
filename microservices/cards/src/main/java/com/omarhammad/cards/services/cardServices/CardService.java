package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.CardDTO;
import com.omarhammad.cards.controllers.dto.UpdateCardDTO;
import com.omarhammad.cards.domain.Card;
import com.omarhammad.cards.domain.CardType;
import com.omarhammad.cards.exceptions.CardAlreadyExistsException;
import com.omarhammad.cards.exceptions.InvalidPinCodeException;
import com.omarhammad.cards.repositories.cardRepos.CardsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CardService implements ICardService {

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


        if(!verifyPinCode(card.getPinCode(),updateCardDTO.getPinCode()))
            throw new InvalidPinCodeException("Invalid Pin Code! try again");

        card.setCardType(CardType.fromString(updateCardDTO.getCardType()));
        card.setTotalLimit(updateCardDTO.getTotalLimit());
        card.setMobileNumber(updateCardDTO.getMobileNumber());

        cardsRepository.save(card);

    }


    private boolean verifyPinCode(String encodedPinCOde, String pinCode) {

        return passwordEncoder.matches(pinCode, encodedPinCOde);
    }
}
