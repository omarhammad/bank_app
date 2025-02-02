package com.omarhammad.cards.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold ErrorResponse information")

public class ErrorResponseDTO {
    @Schema(description = "API path invoked by client")
    private String apiPath;

    @Schema(description = "Error Code representing the error happened",example = "501 INTERNAL_SERVER_ERROR")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error happened")
    private String errorMessage;

    @Schema(description = "Time representing when the error happened")
    private LocalDateTime errorTime;
}

