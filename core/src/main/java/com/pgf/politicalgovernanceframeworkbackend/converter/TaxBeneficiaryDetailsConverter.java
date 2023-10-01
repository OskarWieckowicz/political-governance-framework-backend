package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.TaxBeneficiaryDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxBeneficiaryDetailsConverter {

    TaxBeneficiaryDetails taxBeneficiaryDetailsDtoToTaxBeneficiaryDetails(
        TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto);

    TaxBeneficiaryDetailsDto taxBeneficiaryDetailsToTaxBeneficiaryDetailsDto(
        TaxBeneficiaryDetails taxBeneficiaryDetails);
}
