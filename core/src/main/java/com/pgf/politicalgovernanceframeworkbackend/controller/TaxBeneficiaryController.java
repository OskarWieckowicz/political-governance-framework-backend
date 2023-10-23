package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryIndividualDto;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryDetailsService;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryIndividualService;
import java.io.IOException;
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

    private final TaxBeneficiaryIndividualService beneficiaryService;
    private final TaxBeneficiaryDetailsService beneficiaryDetailsService;

    @GetMapping
    List<TaxBeneficiaryIndividualDto> getTaxBeneficiaries(Principal principal) {
        List<TaxBeneficiaryIndividualDto> taxBeneficiaries =
            beneficiaryService.getTaxBeneficiaries(principal.getName());
        return taxBeneficiaries;
    }

    @GetMapping("/details/{name}")
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(@PathVariable String name) throws IOException {
        TaxBeneficiaryDetailsDto taxBeneficiaryDetails = beneficiaryDetailsService.getTaxBeneficiaryDetails(name);
        return taxBeneficiaryDetails;

    }
}
