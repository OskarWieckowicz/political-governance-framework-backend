package com.pgf.politicalgovernanceframeworkbackend.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaxDistributionDto {
    @NotNull
    private String destination;

    @NotNull
    private int percentage;

    @NotNull
    private BigInteger toBePaid;
}
