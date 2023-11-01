package com.pgf.politicalgovernanceframeworkbackend.dto;

import com.pgf.politicalgovernanceframeworkbackend.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DocumentDto {
    private String date;
    private float amount;
    private TransactionTypeEnum type;
    private String fileName;
    private String key;
}
