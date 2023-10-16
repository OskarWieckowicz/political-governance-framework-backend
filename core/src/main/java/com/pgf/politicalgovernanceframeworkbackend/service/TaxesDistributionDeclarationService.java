package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.TaxDistributionConverter;
import com.pgf.politicalgovernanceframeworkbackend.converter.TaxesDistributionDeclarationConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxDistribution;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxesDistributionDeclaration;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.TaxesDistributionDeclarationRepository;
import com.pgf.politicalgovernanceframeworkbackend.utils.EthereumUtils;
import java.math.BigInteger;
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
    private final CryptoPriceService cryptoPriceService;
    private final DeclarationService declarationService;

    public TaxesDistributionDeclarationDto findByUserId(String userId) {
        // TODO: fetch default values from external config
        TaxesDistributionDeclaration defaultTaxesDistributionDeclaration = TaxesDistributionDeclaration.builder()
            .distributions(
                List.of(
                    TaxDistribution.builder().destination("Education").percentage(40).build(),
                    TaxDistribution.builder().destination("Health Care").percentage(20).build(),
                    TaxDistribution.builder().destination("Army").percentage(10).build(),
                    TaxDistribution.builder().destination("Infrastructure").percentage(20).build(),
                    TaxDistribution.builder().destination("European Union").percentage(10).build()
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
        DeclarationDto currentDeclaration = declarationService.getCurrentDeclaration(userId);

        TaxesDistributionDeclarationDto taxesDistributionDeclarationDto = this.findByUserId(userId);

        taxDistributionDtos.forEach(
            taxDistributionDto -> taxDistributionDto.setToBePaid(
                getToBePaidValue(
                    currentDeclaration.getTaxes(),
                    taxesDistributionDeclarationDto,
                    taxDistributionDto.getDestination())
            )
        );

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

    private BigInteger getToBePaidValue(float taxes,
                                        TaxesDistributionDeclarationDto taxesDistributionDeclarationDto,
                                        String nameOfTheBeneficiary) {
        double percentage = getPercentage(taxesDistributionDeclarationDto, nameOfTheBeneficiary).doubleValue();
        double plnValue = percentage / 100 * taxes;
        double ethPrice = cryptoPriceService.getEthPrice().getPrice();
        double ethValue = ethPrice == 0 ? 0 : plnValue / ethPrice;
        return EthereumUtils.ethToWei(ethValue);
    }

    private static Integer getPercentage(TaxesDistributionDeclarationDto taxesDistributionDeclarationDto,
                                         String nameOfTheBeneficiary) {
        return taxesDistributionDeclarationDto.getDistributions().stream()
            .filter(taxDistributionDto -> taxDistributionDto.getDestination().equals(nameOfTheBeneficiary))
            .findFirst()
            .map(TaxDistributionDto::getPercentage)
            .orElseThrow(() -> new NotFoundException("Percentage not found!"));
    }
}
