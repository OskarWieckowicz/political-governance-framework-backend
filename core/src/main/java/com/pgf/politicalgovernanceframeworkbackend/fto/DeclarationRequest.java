package com.pgf.politicalgovernanceframeworkbackend.fto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeclarationRequest {

    @NotNull(message = "The revenue is required")
    @Min(value = 0, message = "The revenue can't be smaller than than 0")
    private float revenue;

    @NotNull(message = "The expense is required")
    @Min(value = 0, message = "The expense can't be smaller than than 0")
    private float expense;
}
