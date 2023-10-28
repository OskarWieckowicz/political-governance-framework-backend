package com.pgf.politicalgovernanceframeworkbackend.dto;

import com.pgf.politicalgovernanceframeworkbackend.enums.TransactionTypeEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DocumentDto {
    private String date;
    private float amount;
    private TransactionTypeEnum type;
    private String fileName;
    private String key;
}
