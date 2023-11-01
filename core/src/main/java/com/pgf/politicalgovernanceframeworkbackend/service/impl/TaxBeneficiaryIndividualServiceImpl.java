package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static com.pgf.politicalgovernanceframeworkbackend.utils.DateUtils.getLastBillingPeriod;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxBeneficiaryConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryIndividual;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxBeneficiaryIndividualRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryIndividualService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxBeneficiaryIndividualServiceImpl implements TaxBeneficiaryIndividualService {
    private final TaxBeneficiaryIndividualRepository repository;
    private final TaxBeneficiaryConverter converter;

    public List<TaxBeneficiaryIndividualDto> getTaxBeneficiaries(String userId) {
        List<TaxBeneficiaryIndividual> taxBeneficiaries =
            repository.getAllTaxBeneficiariesIndividuals(userId, getLastBillingPeriod());
        return taxBeneficiaries.stream().map(converter::taxBeneficiaryToTaxBeneficiaryDto).toList();
    }

    public Map<String, Integer> getDefaultTaxesPercentages() {
        List<TaxBeneficiaryIndividual> taxBeneficiaries = repository.findAll();

        Map<String, Integer> taxPercentageMap = new HashMap<>();
        for (TaxBeneficiaryIndividual beneficiary : taxBeneficiaries) {
            taxPercentageMap.put(beneficiary.getName(), beneficiary.getDefaultTaxPercentage());
        }

        return taxPercentageMap;
    }
}
