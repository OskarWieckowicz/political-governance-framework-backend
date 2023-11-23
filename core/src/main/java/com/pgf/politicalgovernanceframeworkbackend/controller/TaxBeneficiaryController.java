package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.TaxBeneficiaryDetailsServiceImpl;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.TaxBeneficiaryIndividualServiceImpl;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/taxBeneficiaries")
class TaxBeneficiaryController {

    private final TaxBeneficiaryIndividualServiceImpl beneficiaryService;
    private final TaxBeneficiaryDetailsServiceImpl beneficiaryDetailsService;

    @GetMapping
    List<TaxBeneficiaryIndividualDto> getTaxBeneficiaries(Principal principal) {
        return beneficiaryService.getTaxBeneficiaries(principal.getName());
    }

    @GetMapping("/details/{name}")
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(@NotNull( message = "Name cannot be null") @PathVariable String name) {
        return beneficiaryDetailsService.getTaxBeneficiaryDetails(name);
    }
}
