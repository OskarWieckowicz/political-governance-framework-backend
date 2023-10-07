package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.DocumentConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Document;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.DocumentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentConverter converter;

    public List<DocumentDto> getAllDocuments(String userId) {
        List<Document> documents = repository.findAllByUserId(userId);
        return documents.stream().map(converter::documentToDocumentDto).toList();
    }

    public DocumentDto createDocument(DocumentDto documentDto, String userId) {
        Document document = converter.documentDtoToDocument(documentDto);
        document.setUserId(userId);
        Document savedDocument = repository.save(document);
        return converter.documentToDocumentDto(savedDocument);
    }

}
