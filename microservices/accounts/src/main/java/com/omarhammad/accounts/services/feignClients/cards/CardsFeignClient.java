package com.omarhammad.accounts.services.feignClients.cards;

import com.omarhammad.accounts.controllers.dtos.cards.CardDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/v1/cards", consumes = "application/json")
    ResponseEntity<CardDTO> getCardDetails(@RequestParam String phoneNumber);
}
