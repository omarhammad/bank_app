package com.omarhammad.accounts.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(name = "Response", description = "Schema to hold successful response information")
@AllArgsConstructor
public class ResponseDTO {

    @Schema(description = "Status code in the response", example = "200")
    private int statusCode;

    @Schema(description = "Status message in the response", example = "Request processed successfully")
    private String statusMsg;
}
