package com.pgf.politicalgovernanceframeworkbackend.dto;

import java.math.BigInteger;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentReceivedEventDto {
    private UUID id;
    public String taxIdentifier;
    public BigInteger amount;
    private String transactionHash;
    private BigInteger timestamp;
    private BigInteger blockNumber;
    private String contractAddress;
}
