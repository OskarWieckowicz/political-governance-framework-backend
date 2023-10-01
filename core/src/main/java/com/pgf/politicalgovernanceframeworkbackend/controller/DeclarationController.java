package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.request.DeclarationRequest;
import com.pgf.politicalgovernanceframeworkbackend.entity.keycloak.User;
import com.pgf.politicalgovernanceframeworkbackend.repository.keycloak.UserRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.DeclarationService;
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

    private final DeclarationService service;
    private final UserRepository repository;

    @GetMapping("/history")
    List<DeclarationDto> getDeclarationHistory(Principal principal) {
        principal.getName();
        List<User> all = repository.findAll();
        return service.findAllDeclarations();
    }

    @PutMapping
    DeclarationDto addDeclaration(@RequestBody DeclarationRequest declarationRequestBody) {
        return service.createOrUpdateDeclaration(declarationRequestBody);
    }

    @GetMapping
    DeclarationDto getCurrentDeclaration() {
        return service.getCurrentDeclaration();
    }
}
