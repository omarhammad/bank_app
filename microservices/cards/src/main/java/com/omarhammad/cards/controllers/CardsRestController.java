package com.omarhammad.cards.controllers;

import com.omarhammad.cards.controllers.dto.*;
import com.omarhammad.cards.services.cardServices.ICardService;
import com.omarhammad.cards.utils.phoneNumberValidator.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Cards", description = "Cards Management APIs")
@RequestMapping(value = "/api/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsRestController {


    /*
     * TODO:
     *    1) Get Card Info - DONE
     *    2) Create Card - DONE
     *    3) Update Card - DONE
     *    4) Delete Card - DONE
     *    5) Withdraw Money - DONE
     *    6) Deposit Money - DONE
     *    7) Change PinCode - DONE
     * TODO LATER:
     *    1) Request Current PinCode with email - WHILE DOING THE MICRO SERVICES
     *    2) Un/Block Card - WHILE DOING THE MICRO SERVICES
     *    3) Transactions History - WHILE DOING THE MICRO SERVICES
     *    4) Make another Transaction MicroServices when needed contains (DEPOSIT,WITHDRAW AND HISTORY)
     *       for Depth in learning.
     * */

    private ICardService cardService;
    private CardsContactInfoDTO cardsContactInfoDTO;

    @Operation(summary = "Fetch Card REST API", description = "REST API to fetch a customer card")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("")
    public ResponseEntity<CardDTO> getCardDetails(@RequestParam @ValidPhoneNumber @Schema(example = "+32465123456") String phoneNumber) {

        CardDTO CardDTO = cardService.getCard(phoneNumber);
        return ResponseEntity.ok(CardDTO);
    }


    @Operation(summary = "Create Card REST API", description = "REST API  to create a credit/debit card for customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),

    })



    @PostMapping("")
    public ResponseEntity<ResponseDTO> createCard(@RequestBody @Valid CreateCardDTO createCardDTO) {

        cardService.createCard(createCardDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED, "Card Created Successfully"));
    }


    @Operation(summary = "Update Card REST API", description = "REST API to update the card information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "HTTP Status UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @PutMapping("")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody @Valid UpdateCardDTO updateCardDTO) {

        cardService.updateCard(updateCardDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, "Card Updated Successfully"));
    }


    @Operation(summary = "Change Pin code REST API", description = "REST API to change the pin code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "HTTP Status UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @PatchMapping("/{cardNumber}/pincode")
    public ResponseEntity<ResponseDTO> changePinCode(@RequestBody @Valid PinCodeChangeDTO pinCodeChangeDTO, @PathVariable @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
    String cardNumber) {

        cardService.changePinCode(cardNumber, pinCodeChangeDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, "Pin code changed successfully"));
    }


    @Operation(summary = "Delete Card REST API", description = "REST API to delete a card")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "HTTP Status UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @DeleteMapping("")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestBody DeleteRequestDTO deleteRequestDTO) {
        cardService.deleteCard(deleteRequestDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, "Card deleted successfully"));
    }


    @Operation(summary = "Transaction REST API", description = "REST API to make a transaction for a customer's card")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "HTTP Status UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "HTTP Status NOT_FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @PostMapping("/{cardNumber}/transaction")
    public ResponseEntity<ResponseDTO> makeTransaction(@RequestBody @Valid TransactionDTO transactionDTO,
                                                       @PathVariable @CreditCardNumber(ignoreNonDigitCharacters = true, message = "Invalid card number")
                                                       String cardNumber) {

        cardService.makeTransaction(cardNumber, transactionDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED, "Transaction made successfully"));
    }


    @Operation(
            summary = "Get Contact Info using @ConfigurationProperties",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(value = "/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDTO);
    }


}
