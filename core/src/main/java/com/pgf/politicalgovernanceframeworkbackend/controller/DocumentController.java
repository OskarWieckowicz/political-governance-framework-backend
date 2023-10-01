package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.service.DocumentService;
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
    List<DocumentDto> getDocuments() {
        return service.getAllDocuments();
    }


    @PostMapping
    DocumentDto addDocument(@RequestBody DocumentDto documentDto) {
        DocumentDto document = this.service.createDocument(documentDto);
        return document;
    }
}
