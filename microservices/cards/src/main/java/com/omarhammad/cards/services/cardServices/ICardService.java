package com.omarhammad.cards.services.cardServices;

import com.omarhammad.cards.controllers.dto.CardDTO;
import org.springframework.stereotype.Repository;

public interface ICardService {

    CardDTO getCard(String mobileNumber);
}
