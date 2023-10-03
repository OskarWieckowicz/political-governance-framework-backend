package com.pgf.politicalgovernanceframeworkbackend.fto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeclarationRequest {
    private float revenue;
    private float expense;
}
