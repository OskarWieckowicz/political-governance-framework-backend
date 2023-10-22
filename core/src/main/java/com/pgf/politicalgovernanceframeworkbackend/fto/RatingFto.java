package com.pgf.politicalgovernanceframeworkbackend.fto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RatingFto {
    private float value;
    private long taxBeneficiaryId;
}
