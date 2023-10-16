package com.pgf.politicalgovernanceframeworkbackend.dto;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaxDistributionDto {
    private String destination;
    private int percentage;
    private BigInteger toBePaid;
}
