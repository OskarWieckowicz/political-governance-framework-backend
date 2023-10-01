package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxBeneficiaryConverter {
    TaxBeneficiary taxBeneficiaryDtoToTaxBeneficiary(TaxBeneficiaryDto taxBeneficiaryDto);

    TaxBeneficiaryDto taxBeneficiaryToTaxBeneficiaryDto(TaxBeneficiary taxBeneficiary);
}
