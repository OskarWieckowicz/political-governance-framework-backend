package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.DocumentConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.Document;
import com.pgf.politicalgovernanceframeworkbackend.repository.DocumentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentConverter converter;

    public List<DocumentDto> getAllDocuments() {
        List<Document> documents = repository.findAll();
        return documents.stream().map(converter::documentToDocumentDto).toList();
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = converter.documentDtoToDocument(documentDto);
        Document savedDocument = repository.save(document);
        return converter.documentToDocumentDto(savedDocument);
    }

}
