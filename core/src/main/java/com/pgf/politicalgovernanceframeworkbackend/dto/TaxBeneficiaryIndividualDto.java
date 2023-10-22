package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaxBeneficiaryIndividualDto {
    private long id;
    private String name;
    private String description;
    private String image;
    private float rating;
}
