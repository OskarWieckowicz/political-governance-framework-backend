package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxBeneficiaryConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiary;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxBeneficiaryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxBeneficiaryService {
    private final TaxBeneficiaryRepository repository;
    private final TaxBeneficiaryConverter converter;

    public List<TaxBeneficiaryDto> getTaxBeneficiaries() {
        List<TaxBeneficiary> taxBeneficiaries = repository.findAll();
        return taxBeneficiaries.stream().map(converter::taxBeneficiaryToTaxBeneficiaryDto).toList();
    }

    public void saveBeneficiary(TaxBeneficiaryDto taxBeneficiaryDto) {
        repository.save(converter.taxBeneficiaryDtoToTaxBeneficiary(taxBeneficiaryDto));
    }
}
