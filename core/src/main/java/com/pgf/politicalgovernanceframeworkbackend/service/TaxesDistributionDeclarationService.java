package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import java.util.List;

public interface TaxesDistributionDeclarationService {
    TaxesDistributionDeclarationDto findByUserId(String userId);
    TaxesDistributionDeclarationDto createTaxesDistributionDeclaration(
        List<TaxDistributionDto> taxDistributionDtos, String userId);

}
