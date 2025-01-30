package com.omarhammad.loans.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold Response information")
public class ResponseDTO {

    @Schema(description = "The response status code", example = "200")
    private HttpStatus statusCode;

    @Schema(description = "The response success message ", example = "Loan created Successfully")
    private String SuccessMsg;

}
