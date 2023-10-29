package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tax_beneficiary")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TaxBeneficiaryIndividual extends TaxBeneficiary {

    @Column(name = "short_description")
    private String description;

    private int defaultTaxPercentage;

    public TaxBeneficiaryIndividual(Long id, String image, String name, float rating, String description) {
        super(id, image, name, rating);
        this.description = description;
    }
}

