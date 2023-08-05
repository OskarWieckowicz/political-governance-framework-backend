package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DocumentDto {
    private String date;
    private float amount;
    private String type;
    private String file;
}
