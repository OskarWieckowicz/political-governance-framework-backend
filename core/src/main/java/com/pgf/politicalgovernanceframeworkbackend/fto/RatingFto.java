package com.pgf.politicalgovernanceframeworkbackend.fto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RatingFto {

    @NotNull(message = "Rating value cannot be null")
    @Max(value = 5, message = "Max rating value is 5")
    @Min(value = 0, message = "Min rating value is 0")
    private float value;

    @NotNull
    private long taxBeneficiaryId;
}
