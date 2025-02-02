package com.omarhammad.cards.controllers;

import com.omarhammad.cards.controllers.dto.CardDTO;
import com.omarhammad.cards.controllers.dto.ErrorResponseDTO;
import com.omarhammad.cards.services.cardServices.ICardService;
import com.omarhammad.cards.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @Operation(summary = "Fetch Card REST API", description = "REST API to fetch a customer card")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("")
    public ResponseEntity<CardDTO> getCardDetails(@RequestParam @ValidPhoneNumber String mobileNumber) {

        CardDTO cardDTO = cardService.getCard(mobileNumber);
        return ResponseEntity.ok(cardDTO);
    }


}
