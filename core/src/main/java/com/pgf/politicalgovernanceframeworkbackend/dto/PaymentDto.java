package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentDto {
    private int percentage;
    private String destination;
    private String contractAddress;
    private float value;
    private float paid;
}
