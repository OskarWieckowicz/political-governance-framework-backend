package com.pgf.politicalgovernanceframeworkbackend.dto;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentDto {
    private int percentage;
    private String destination;
    private String contractAddress;
    private BigInteger toBePaid;
    private BigInteger paid;
    private BigInteger leftToPay;
}
