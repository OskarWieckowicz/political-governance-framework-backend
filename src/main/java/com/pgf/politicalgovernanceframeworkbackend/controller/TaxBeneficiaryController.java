package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        TaxBeneficiaryDto beneficiary3 = TaxBeneficiaryDto.builder()
                .name("European Union")
                .description("Show your support for the European Union and its efforts towards economic integration, peace, and cooperation among member countries.")
                .img("/eu.jpg")
                .rating(1f)
                .build();

        TaxBeneficiaryDto beneficiary4 = TaxBeneficiaryDto.builder()
                .name("Army")
                .description("Contribute to the defense and security of the nation by supporting the armed forces and their missions.")
                .img("/army.jpg")
                .rating(5f)
                .build();

        TaxBeneficiaryDto beneficiary5 = TaxBeneficiaryDto.builder()
                .name("Ministry of Infrastructure")
                .description("Support the development and maintenance of national infrastructure projects, such as roads, bridges, and public transportation systems.")
                .img("/infrastructure.jpg")
                .rating(2f)
                .build();

        // Creating a Flux of TaxBeneficiaryDto
        return List.of(beneficiary1, beneficiary2, beneficiary3, beneficiary4, beneficiary5);
    }

    @GetMapping("/details/{name}")
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(@PathVariable String name) {
        return TaxBeneficiaryDetailsDto.builder()
                .image("/eu.img")
                .name("European Union")
                .description("The main and central goal of EU development cooperation is the eradication of poverty...")
                .site("https://european-union.europa.eu/")
                .leader("Ursula von der Leyen")
                .smartContractAddress("0x06f333ca1c1b3d08f487d67a5a377cb92d3695ba85d4cc30855733d6a160caba")
                .balance(124414.1151)
                .citizensSatisfaction(2.98)
                .build();
    }
}
