package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxDistributionConverter;
import com.pgf.politicalgovernanceframeworkbackend.converter.TaxesDistributionDeclarationConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxDistribution;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxesDistributionDeclaration;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxesDistributionDeclarationRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaxesDistributionDeclarationService {

    private final TaxesDistributionDeclarationRepository repository;
    private final TaxesDistributionDeclarationConverter taxesDistributionDeclarationConverter;
    private final TaxDistributionConverter taxDistributionConverter;

    public TaxesDistributionDeclarationDto findByUserId(String userId) {
        // TODO: fetch default values from external config
        TaxesDistributionDeclaration defaultTaxesDistributionDeclaration = TaxesDistributionDeclaration.builder()
            .distributions(
                List.of(
                    TaxDistribution.builder().destination("Education").percentage(40).build(),
                    TaxDistribution.builder().destination("Health Care").percentage(20).build(),
                    TaxDistribution.builder().destination("Army").percentage(10).build(),
                    TaxDistribution.builder().destination("Infrastructure").percentage(20).build(),
                    TaxDistribution.builder().destination("UE").percentage(10).build()
                )
            ).submitted(false).build();
        Optional<TaxesDistributionDeclaration> optionalTaxesDistributionDeclaration =
            repository.findFirstByUserId(userId);
        TaxesDistributionDeclaration taxesDistributionDeclaration =
            optionalTaxesDistributionDeclaration.orElse(defaultTaxesDistributionDeclaration);
        return taxesDistributionDeclarationConverter.convertTo(taxesDistributionDeclaration);
    }

    public TaxesDistributionDeclarationDto createTaxesDistributionDeclaration(
        List<TaxDistributionDto> taxDistributionDtos, String userId) {
        // TODO: add distribution sum eq 100 validation
        List<TaxDistribution> taxDistributionList =
            taxDistributionDtos.stream().map(taxDistributionConverter::convertFrom).toList();
        TaxesDistributionDeclaration taxesDistributionDeclaration = TaxesDistributionDeclaration.builder()
            .distributions(taxDistributionList)
            .submitted(true)
            .userId(userId)
            .build();
        for (TaxDistribution taxDistribution : taxDistributionList) {
            taxDistribution.setTaxDistributionDeclaration(taxesDistributionDeclaration);
        }
        return taxesDistributionDeclarationConverter.convertTo(repository.save(taxesDistributionDeclaration));
    }
}
