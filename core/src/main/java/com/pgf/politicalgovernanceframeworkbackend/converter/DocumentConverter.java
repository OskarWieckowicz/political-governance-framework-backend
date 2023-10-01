package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentConverter {
    DocumentDto documentToDocumentDto(Document source);

    Document documentDtoToDocument(DocumentDto documentDto);
}
