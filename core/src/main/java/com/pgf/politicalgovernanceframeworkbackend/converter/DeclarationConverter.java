package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Declaration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeclarationConverter {
    Declaration declarationDtoToDeclaration(DeclarationDto declarationDto);
    DeclarationDto declarationToDeclarationDto(Declaration declaration);
}
