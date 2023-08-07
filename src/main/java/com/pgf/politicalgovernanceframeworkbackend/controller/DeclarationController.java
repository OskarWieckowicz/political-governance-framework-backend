package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/declaration")
class DeclarationController {

    @GetMapping("/history")
    List<DeclarationDto> getDeclarationHistory() {
        return List.of(DeclarationDto.builder()
                        .billingPeriod("05.2023")
                        .revenue(1200)
                        .expense(213)
                        .income(24)
                        .taxes(5).build(),
                DeclarationDto.builder()
                        .billingPeriod("06.2023")
                        .revenue(200)
                        .expense(23)
                        .income(210)
                        .taxes(50).build());
    }

    @PostMapping()
    DeclarationDto addDeclaration() {
        return DeclarationDto.builder()
                .billingPeriod("11.2023")
                .revenue(1200)
                .expense(213)
                .income(24)
                .taxes(5).build();
    }
}
