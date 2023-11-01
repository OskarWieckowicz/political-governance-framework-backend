package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DeclarationRequest;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.DeclarationServiceImpl;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/declaration")
@AllArgsConstructor
class DeclarationController {

    private final DeclarationServiceImpl declarationService;

    @GetMapping("/history")
    List<DeclarationDto> getDeclarationHistory(Principal principal) {
        return declarationService.findAllDeclarations(principal.getName());
    }

    @PutMapping
    DeclarationDto addDeclaration(@Valid @RequestBody DeclarationRequest declarationRequestBody, Principal principal) {
        return declarationService.createOrUpdateDeclaration(declarationRequestBody, principal.getName());
    }

    @GetMapping
    DeclarationDto getCurrentDeclaration(Principal principal) {
        return declarationService.getCurrentDeclaration(principal.getName());
    }
}
