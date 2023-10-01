package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxBeneficiaryDetailsConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryDetails;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxBeneficiaryDetailsRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxBeneficiaryDetailsService {
    private final TaxBeneficiaryDetailsRepository repository;
    private final TaxBeneficiaryDetailsConverter converter;

    public void saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto) {
        TaxBeneficiaryDetails taxBeneficiaryDetails =
            converter.taxBeneficiaryDetailsDtoToTaxBeneficiaryDetails(taxBeneficiaryDetailsDto);
        TaxBeneficiaryDetails save = repository.save(taxBeneficiaryDetails);
        System.out.println(save);
    }

    public TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(String name) {
        Optional<TaxBeneficiaryDetails> byName = repository.findByName(name);
        TaxBeneficiaryDetails taxBeneficiaryDetails =
            byName.orElseThrow(() -> new NotFoundException("Tax beneficiary with name " + name + " not found"));
        return converter.taxBeneficiaryDetailsToTaxBeneficiaryDetailsDto(taxBeneficiaryDetails);
    }

    public List<TaxBeneficiaryDetailsDto> getAllTaxBeneficiariesDetails() {
        List<TaxBeneficiaryDetails> taxBeneficiaryDetails = repository.findAll();
        return taxBeneficiaryDetails.stream().map(converter::taxBeneficiaryDetailsToTaxBeneficiaryDetailsDto).toList();
    }
}
