package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryIndividual;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxBeneficiaryConverter {
    TaxBeneficiaryIndividual taxBeneficiaryDtoToTaxBeneficiary(TaxBeneficiaryIndividualDto taxBeneficiaryDto);

    TaxBeneficiaryIndividualDto taxBeneficiaryToTaxBeneficiaryDto(TaxBeneficiaryIndividual taxBeneficiary);
}
