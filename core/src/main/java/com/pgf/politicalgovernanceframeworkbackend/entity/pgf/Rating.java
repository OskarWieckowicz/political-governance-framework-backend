package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float value;
    private String billingPeriod;
    @Column(name = "tax_beneficiary_id")
    private long taxBeneficiaryId;
    private String userId;
}
