package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserAttributeDto {
    private String id;
    private String name;
    private String value;
}
