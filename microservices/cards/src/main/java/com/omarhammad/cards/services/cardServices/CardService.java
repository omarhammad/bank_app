package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.CardDTO;
import com.omarhammad.cards.domain.Card;
import com.omarhammad.cards.repositories.cardRepos.CardsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CardService implements ICardService {

    private CardsRepository cardsRepository;
    private ModelMapper modelMapper;

    @Override
    public CardDTO getCard(String mobileNumber) {

        Card card = cardsRepository.findCardByMobileNumber(mobileNumber)
                .orElseThrow(() -> new EntityNotFoundException("Card with this mobile number %s not found".formatted(mobileNumber)));

        card.setPinCode(null);

        return modelMapper.map(card, CardDTO.class);
    }
}
