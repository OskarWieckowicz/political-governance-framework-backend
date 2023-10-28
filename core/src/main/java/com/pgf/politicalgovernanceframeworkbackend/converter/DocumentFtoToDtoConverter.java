package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DocumentFto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface DocumentFtoToDtoConverter {

    @Mapping(target="fileName", source="documentFto.file", qualifiedByName = "fileConverter")
    DocumentDto convertTo(DocumentFto documentFto, String key);

    @Named("fileConverter")
    static String fileConverter(MultipartFile file) {
        return file.getOriginalFilename();
    }
}
