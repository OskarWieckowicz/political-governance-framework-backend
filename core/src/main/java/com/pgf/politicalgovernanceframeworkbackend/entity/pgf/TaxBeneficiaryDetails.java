package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigInteger;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class TaxBeneficiaryDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String image;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String site;
    private String leader;
    private String smartContractAddress;
    private BigInteger balance;
    private Float citizensSatisfaction;
}
