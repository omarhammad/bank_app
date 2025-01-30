package com.omarhammad.loans.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(name = "Repayment", description = "Schema to hold Repayment information")

public class RepaymentDTO {




    @Positive(message = "amount should be greater than zero")
    @Schema(description = "")
    private Long amount;


}
