package com.omarhammad.cards.controllers;

import com.omarhammad.cards.services.cardServices.ICardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Cards", description = "Cards Management APIs")
@RequestMapping(value = "/api/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsRestController {


    /*
     * TODO:
     *    1) Get Card Info
     *    2) Create Card
     *    3) Update Card
     *    4) Delete Card
     *    5) Withdraw Money
     *    6) Deposit Money
     *    7) Change PinCode
     *    8) Request Current PinCode with email
     *    9) Block Card
     *    10) Transactions History
     * */

    private ICardService cardService;




}
