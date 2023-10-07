package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.service.DocumentService;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
class DocumentController {

    private final DocumentService service;

    @GetMapping
    List<DocumentDto> getDocuments(Principal principal) {
        return service.getAllDocuments(principal.getName());
    }


    @PostMapping
    DocumentDto addDocument(@RequestBody DocumentDto documentDto, Principal principal) {
        DocumentDto document = this.service.createDocument(documentDto, principal.getName());
        return document;
    }
}
