package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DeclarationRequest;
import java.util.List;

public interface DeclarationService {
    DeclarationDto createOrUpdateDeclaration(DeclarationRequest declarationRequest, String userId);
    DeclarationDto getCurrentDeclaration(String userId);
    List<DeclarationDto> findAllDeclarations(String userId);
}
