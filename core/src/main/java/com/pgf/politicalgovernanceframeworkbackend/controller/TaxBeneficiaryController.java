package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryDetailsService;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryService;
import java.math.BigInteger;
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

    private final TaxBeneficiaryService beneficiaryService;
    private final TaxBeneficiaryDetailsService beneficiaryDetailsService;

    @GetMapping
    List<TaxBeneficiaryDto> getTaxBeneficiaries() {
        return beneficiaryService.getTaxBeneficiaries();
    }

    @GetMapping("/details/{name}")
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(@PathVariable String name) {
        return beneficiaryDetailsService.getTaxBeneficiaryDetails(name);
    }
}
