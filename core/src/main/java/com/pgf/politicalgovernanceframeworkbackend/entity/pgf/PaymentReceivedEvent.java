package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PaymentReceivedEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    public String taxIdentifier;
    public BigInteger amount;
    @Column(unique = true)
    private String transactionHash;
    private BigInteger timestamp;
    private BigInteger blockNumber;
    private String contractAddress;
}
