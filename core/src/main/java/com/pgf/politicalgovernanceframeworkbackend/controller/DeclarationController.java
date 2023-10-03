package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DeclarationRequest;
import com.pgf.politicalgovernanceframeworkbackend.service.DeclarationService;
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

    private final DeclarationService declarationService;

    @GetMapping("/history")
    List<DeclarationDto> getDeclarationHistory() {
        return declarationService.findAllDeclarations();
    }

    @PutMapping
    DeclarationDto addDeclaration(@RequestBody DeclarationRequest declarationRequestBody) {
        return declarationService.createOrUpdateDeclaration(declarationRequestBody);
    }

    @GetMapping
    DeclarationDto getCurrentDeclaration() {
        return declarationService.getCurrentDeclaration();
    }
}
