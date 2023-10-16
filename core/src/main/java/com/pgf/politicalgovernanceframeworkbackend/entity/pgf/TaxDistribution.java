package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxDistribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private int percentage;
    private BigInteger toBePaid;
    @ManyToOne
    @JoinColumn(name = "taxes_distribution_declaration_id")
    private TaxesDistributionDeclaration taxDistributionDeclaration;
}
