package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taxBeneficiaries")
class TaxBeneficiaryController {

    @GetMapping
    List<TaxBeneficiaryDto> getTaxBeneficiaries() {

        TaxBeneficiaryDto beneficiary1 = TaxBeneficiaryDto.builder()
                .name("Education")
                .description("Support public education initiatives and programs aimed at providing quality education to students.")
                .img("/education.jpg")
                .rating(4.5f)
                .build();

        TaxBeneficiaryDto beneficiary2 = TaxBeneficiaryDto.builder()
                .name("Health Car")
                .description("Contribute to public healthcare services and initiatives that improve access to medical care and promote well-being.")
                .img("/health-care.jpg")
                .rating(3.8f)
                .build();

        // Creating a Flux of TaxBeneficiaryDto
        return List.of(beneficiary1, beneficiary2);
    }
}
