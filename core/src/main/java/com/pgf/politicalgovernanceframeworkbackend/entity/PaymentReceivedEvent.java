package com.pgf.politicalgovernanceframeworkbackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Data
public class PaymentReceivedEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    public String taxIdentifier;
    public BigInteger amount;
    private String transactionHash;
    private BigInteger timestamp;
    private BigInteger blockNumber;
}
