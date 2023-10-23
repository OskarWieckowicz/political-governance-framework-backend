package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxBeneficiaryDetailsConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryDetails;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxBeneficiaryDetailsRepository;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxBeneficiaryDetailsService {
    private final TaxBeneficiaryDetailsRepository repository;
    private final TaxBeneficiaryDetailsConverter converter;
    private final Web3Service web3Service;

    public TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(String name) throws IOException {
        TaxBeneficiaryDetails tbd = null;
        List<Object[]> results = repository.findByNameAndCalculateAverageRating(name);
        for (Object[] result : results) {
            tbd = (TaxBeneficiaryDetails) result[0];
            Double avgRating = (Double) result[1];
            tbd.setRating(avgRating.floatValue());
        }
        TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto =
            converter.taxBeneficiaryDetailsToTaxBeneficiaryDetailsDto(tbd);
        if(Objects.nonNull(taxBeneficiaryDetailsDto)) {
            taxBeneficiaryDetailsDto.setBalance(web3Service.getEthBalance(taxBeneficiaryDetailsDto.getSmartContractAddress()));
        }
        return taxBeneficiaryDetailsDto;
    }

    public List<TaxBeneficiaryDetailsDto> getAllTaxBeneficiariesDetails() {
        List<TaxBeneficiaryDetails> taxBeneficiaryDetails = repository.findAll();
        return taxBeneficiaryDetails.stream().map(converter::taxBeneficiaryDetailsToTaxBeneficiaryDetailsDto).toList();
    }
}
