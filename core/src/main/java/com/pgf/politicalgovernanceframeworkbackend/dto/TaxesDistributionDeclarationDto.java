package com.pgf.politicalgovernanceframeworkbackend.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaxesDistributionDeclarationDto {
    boolean submitted;
    List<TaxDistributionDto> distributions;
}
