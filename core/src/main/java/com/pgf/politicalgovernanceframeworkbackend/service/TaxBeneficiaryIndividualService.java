package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import java.util.List;
import java.util.Map;

public interface TaxBeneficiaryIndividualService {
    List<TaxBeneficiaryIndividualDto> getTaxBeneficiaries(String userId);
    Map<String, Integer> getDefaultTaxesPercentages();
}
