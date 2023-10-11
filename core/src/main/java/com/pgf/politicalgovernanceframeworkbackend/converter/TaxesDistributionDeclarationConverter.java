package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxesDistributionDeclaration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxesDistributionDeclarationConverter {
    TaxesDistributionDeclarationDto convertTo(TaxesDistributionDeclaration source);
}
