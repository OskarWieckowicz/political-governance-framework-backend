package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import java.util.List;

public interface TaxBeneficiaryDetailsService {
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(String name);
    List<TaxBeneficiaryDetailsDto> getAllTaxBeneficiariesDetails();
}
