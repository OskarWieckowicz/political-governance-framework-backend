package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaxBeneficiaryDto {
    private String name;
    private String description;
    private String img;
    private float rating;
}
