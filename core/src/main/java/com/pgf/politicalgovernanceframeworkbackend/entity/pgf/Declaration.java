package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String billingPeriod;
    private float revenue;
    private float expense;
    private float income;
    private float taxes;
    private String userId;
}
