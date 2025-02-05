package com.omarhammad.cards.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold successful response information")
public class ResponseDTO {

    @Schema(description = "The http status of the response ", example = "200")
    private HttpStatus httpStatus;

    @Schema(description = "The response message", example = "Something Created Successfully!")
    private String responseMsg;
}
