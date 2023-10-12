package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoPriceDto {
    private String symbol;  // e.g., "ETH/USD"
    private double price;
}
