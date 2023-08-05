package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/declaration")
class DeclarationController {

    @GetMapping("/history")
    DeclarationDto getDeclarationHistory() {
        return DeclarationDto.builder()
                .billingPeriod("05.2023")
                .revenue(1200)
                .expense(213)
                .income(24)
                .taxes(5).build();
    }

    @PostMapping()
    DeclarationDto addDeclaration() {
        return DeclarationDto.builder()
                .billingPeriod("05.2023")
                .revenue(1200)
                .expense(213)
                .income(24)
                .taxes(5).build();
    }
}
