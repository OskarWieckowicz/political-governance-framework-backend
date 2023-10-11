package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaxDistributionDto {
    private String destination;
    private int percentage;
}
