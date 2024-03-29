package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static com.pgf.politicalgovernanceframeworkbackend.utils.DateUtils.getLastBillingPeriod;

import com.pgf.politicalgovernanceframeworkbackend.converter.DeclarationConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DeclarationRequest;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Declaration;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.DeclarationRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.DeclarationService;
import com.pgf.politicalgovernanceframeworkbackend.utils.Constants;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DeclarationServiceImpl implements DeclarationService {
    private final DeclarationConverter converter;
    private final DeclarationRepository repository;

    public DeclarationDto createOrUpdateDeclaration(DeclarationRequest declarationRequest, String userId) {
        Optional<Declaration> declarationOptional = repository.findByBillingPeriodAndUserId(getLastBillingPeriod(), userId);
        if (declarationOptional.isPresent()) {
            float income = declarationRequest.getRevenue() - declarationRequest.getExpense();
            Declaration declaration = declarationOptional.get();
            declaration.setRevenue(declarationRequest.getRevenue());
            declaration.setExpense(declarationRequest.getExpense());
            declaration.setIncome(income);
            declaration.setTaxes(Constants.TAX_PERCENTAGE * income);
            return converter.declarationToDeclarationDto(declaration);
        } else {
            float income = declarationRequest.getRevenue() - declarationRequest.getExpense();
            Declaration declaration = Declaration.builder()
                .revenue(declarationRequest.getRevenue())
                .expense(declarationRequest.getExpense())
                .billingPeriod(getLastBillingPeriod())
                .income(income)
                .taxes(Constants.TAX_PERCENTAGE * income)
                .userId(userId)
                .build();
            Declaration savedDeclaration = repository.save(declaration);
            return converter.declarationToDeclarationDto(savedDeclaration);
        }
    }

    public DeclarationDto getCurrentDeclaration(String userId) {
        Optional<Declaration> declaration = repository.findByBillingPeriodAndUserId(getLastBillingPeriod(), userId);
        return declaration.map(converter::declarationToDeclarationDto).orElse(DeclarationDto.builder().build());
    }

    public List<DeclarationDto> findAllDeclarations(String userId) {
        List<Declaration> foundDeclarations = repository.findAllByUserId(userId);
        return foundDeclarations.stream().map(converter::declarationToDeclarationDto).toList();
    }

}
