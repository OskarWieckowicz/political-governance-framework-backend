package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeclarationDto {
    private String billingPeriod;
    private float revenue;
    private float expense;
    private float income;
    private float taxes;
}
