package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.pgf.politicalgovernanceframeworkbackend.converter.DocumentConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Document;
import com.pgf.politicalgovernanceframeworkbackend.enums.TransactionTypeEnum;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.DocumentRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplTests {
    @Mock
    private DocumentRepository repository;
    @Mock
    private DocumentConverter converter;
    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test
    void shouldSuccessfullyGetAllDocumentsGivenUserId() {
        // Given
        String userId = "user123";
        List<Document> documents = List.of(getExampleDocument());
        given(repository.findAllByUserId(userId)).willReturn(documents);
        given(converter.documentToDocumentDto(any(Document.class))).willReturn(DocumentDto.builder().build());

        // When
        List<DocumentDto> result = documentService.getAllDocuments(userId);

        // Then
        assertThat(result).isNotNull().hasSize(documents.size());
    }

    @Test
    void shouldSuccessfullyCreateDocument() {
        // Given
        DocumentDto documentDto = getExampleDocumentDto();
        Document document = getExampleDocument();
        given(converter.documentDtoToDocument(documentDto)).willReturn(document);
        given(repository.save(document)).willReturn(document);
        given(converter.documentToDocumentDto(document)).willReturn(documentDto);

        // When
        DocumentDto result = documentService.createDocument(documentDto, document.getUserId());

        // Then
        assertThat(result).isNotNull().isEqualTo(getExampleDocumentDto());
    }

    @Test
    void shouldReturnTrueWhenDocumentExists() {
        String key = "document_key";
        String userId = "user123";
        given(repository.findFirstByKeyAndUserId(key, userId)).willReturn(Optional.of(new Document()));

        // When
        boolean result = documentService.hasDocument(key, userId);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenDocumentDoesNotExist() {
        String key = "document_key";
        String userId = "user123";
        given(repository.findFirstByKeyAndUserId(key, userId)).willReturn(Optional.empty());

        // When
        boolean result = documentService.hasDocument(key, userId);

        // Then
        assertThat(result).isFalse();
    }

    private Document getExampleDocument() {
        return new Document(
            1L,
            new Date().toString(),
            1f,
            TransactionTypeEnum.Revenue,
            "fileName", "key",
            "userId");
    }

    private DocumentDto getExampleDocumentDto() {
        return new DocumentDto(
            new Date().toString(),
            1f,
            TransactionTypeEnum.Revenue,
            "fileName",
            "key");
    }

}
