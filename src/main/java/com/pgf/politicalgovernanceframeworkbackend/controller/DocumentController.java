package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
class DocumentController {

    @GetMapping
    List<DocumentDto> getDocuments() {
        DocumentDto documentDto = DocumentDto.builder()
                .date("2023-07-31")
                .amount(123)
                .type("revenue")
                .file("example.pdf").build();
        return List.of(documentDto);
    }


    @PostMapping
    DocumentDto addDocument() {
        DocumentDto documentDto = DocumentDto.builder()
                .date("2023-07-31")
                .amount(123)
                .type("revenue")
                .file("example.pdf").build();
        return documentDto;
    }
}
