package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RatingDto {
    private Long id;
    private float value;
    private String billingPeriod;
    private long taxBeneficiaryId;
    private String userId;
}
