package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.TaxesDistributionDeclarationServiceImpl;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/taxesDistribution")
public class TaxesDistributionController {
    private final TaxesDistributionDeclarationServiceImpl service;

    @GetMapping
    TaxesDistributionDeclarationDto getTaxesDistributionDeclaration(Principal principal) {
        return service.findByUserId(principal.getName());
    }

    @PostMapping
    TaxesDistributionDeclarationDto postTaxesDistributionDeclaration(
        @Valid @RequestBody List<TaxDistributionDto> taxDistributionDtos,
        Principal principal) {
        return service.createTaxesDistributionDeclaration(taxDistributionDtos, principal.getName());
    }

}
