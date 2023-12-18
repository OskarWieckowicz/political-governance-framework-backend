package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import java.util.List;

public interface DocumentService {
    List<DocumentDto> getAllDocuments(String userId);
    DocumentDto createDocument(DocumentDto documentDto, String userId);
    boolean hasDocument(String key, String userId);
    void deleteDocument(String key);
}
