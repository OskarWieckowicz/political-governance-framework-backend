package com.pgf.politicalgovernanceframeworkbackend.dto;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxBeneficiaryDetailsDto {
    private String image;
    private String name;
    private String description;
    private String site;
    private String leader;
    private String smartContractAddress;
    private BigInteger balance;
    private float citizensSatisfaction;
}
