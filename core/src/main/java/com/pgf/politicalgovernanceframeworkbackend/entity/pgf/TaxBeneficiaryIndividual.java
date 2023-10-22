package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    public TaxBeneficiaryIndividual(Long id, String image, String name, float rating, String description) {
        super(id, image, name, rating);
        this.description = description;
    }
}

