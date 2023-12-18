package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.pgf.politicalgovernanceframeworkbackend.converter.DocumentConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Document;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.DocumentRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.DocumentService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentConverter converter;

    @Override
    public List<DocumentDto> getAllDocuments(String userId) {
        List<Document> documents = repository.findAllByUserId(userId);
        return documents.stream().map(converter::documentToDocumentDto).toList();
    }

    @Override
    public DocumentDto createDocument(DocumentDto documentDto, String userId) {
        Document document = converter.documentDtoToDocument(documentDto);
        document.setUserId(userId);
        Document savedDocument = repository.save(document);
        return converter.documentToDocumentDto(savedDocument);
    }

    @Override
    public boolean hasDocument(String key, String userId) {
        Optional<Document> firstByFileAndUserId = repository.findFirstByKeyAndUserId(key, userId);
        return firstByFileAndUserId.isPresent();
    }

    @Transactional
    @Override
    public void deleteDocument(String key) {
        repository.deleteByKey(key);
    }

}
