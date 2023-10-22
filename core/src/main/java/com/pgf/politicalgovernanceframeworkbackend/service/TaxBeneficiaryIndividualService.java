package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxBeneficiaryConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryIndividual;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxBeneficiaryIndividualRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxBeneficiaryIndividualService {
    private final TaxBeneficiaryIndividualRepository repository;
    private final TaxBeneficiaryConverter converter;

    public List<TaxBeneficiaryIndividualDto> getTaxBeneficiaries(String userId) {
        List<TaxBeneficiaryIndividual> taxBeneficiaries =
            repository.getAllTaxBeneficiariesIndividuals(userId, "10.2023");
        return taxBeneficiaries.stream().map(converter::taxBeneficiaryToTaxBeneficiaryDto).toList();
    }

    public void saveBeneficiary(TaxBeneficiaryIndividualDto taxBeneficiaryDto) {
        repository.save(converter.taxBeneficiaryDtoToTaxBeneficiary(taxBeneficiaryDto));
    }
}
