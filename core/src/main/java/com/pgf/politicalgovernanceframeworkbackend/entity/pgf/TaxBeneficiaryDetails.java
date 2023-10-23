package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_beneficiary")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaxBeneficiaryDetails extends TaxBeneficiary {

    @Column(columnDefinition = "TEXT", name = "long_description")
    private String description;
    private String site;
    private String leader;
    private String smartContractAddress;

    public TaxBeneficiaryDetails(Long id, String image, String name, float rating, String description, String site,
                                 String leader, String smartContractAddress) {
        super(id, image, name, rating);
        this.description = description;
        this.site = site;
        this.leader = leader;
        this.smartContractAddress = smartContractAddress;
    }
}
