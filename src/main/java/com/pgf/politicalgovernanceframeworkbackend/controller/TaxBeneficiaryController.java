package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryService;
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

    private final TaxBeneficiaryService service;

    @GetMapping
    List<TaxBeneficiaryDto> getTaxBeneficiaries() {
        return service.getTaxBeneficiaries();
    }

    @GetMapping("/details/{name}")
    TaxBeneficiaryDetailsDto getTaxBeneficiaryDetails(@PathVariable String name) {
        return TaxBeneficiaryDetailsDto.builder()
                .image("/eu.jpg")
                .name("European Union")
                .description("""
                    The main and central goal of EU development cooperation is the
                    eradication of poverty in the context of sustainable development,
                    including the pursuit of the 2030 Agenda for Sustainable Development
                    and its Sustainable Development Goals (SDGs) adopted in 2015 by the
                    United Nations. The EU, together with its Member States, is the
                    largest donor of Official Development Assistance (ODA) in the
                    world. In 2021, the Community together with the Member States
                    (Team Europe) financed over 4,355% of aid on a global scale. The
                    general budget and the European Development Fund (EDF) finance
                    approximately 20% of EU expenditure on development assistance. The
                    rest are Member States' initiatives implemented under national aid
                    schemes. The EU institutions and Member States have committed to
                    jointly achieving an ODA/GNI ratio of 0.7% by 2030."""
                )
                .site("https://european-union.europa.eu/")
                .leader("Ursula von der Leyen")
                .smartContractAddress("0x06f333ca1c1b3d08f487d67a5a377cb92d3695ba85d4cc30855733d6a160caba")
                .balance(124414.1151)
                .citizensSatisfaction(2.98)
                .build();
    }
}
