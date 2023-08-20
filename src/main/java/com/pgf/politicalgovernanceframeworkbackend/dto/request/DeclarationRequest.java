package com.pgf.politicalgovernanceframeworkbackend.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeclarationRequest {
    private float revenue;
    private float expense;
}
