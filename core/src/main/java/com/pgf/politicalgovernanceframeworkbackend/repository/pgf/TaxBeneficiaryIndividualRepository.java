package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryIndividual;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBeneficiaryIndividualRepository extends JpaRepository<TaxBeneficiaryIndividual, Long> {

    @Query("SELECT NEW com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryIndividual(tbi.id, tbi.image, tbi.name, COALESCE(r.value, 0), tbi.description) " +
        "FROM TaxBeneficiaryIndividual tbi " +
        "LEFT JOIN Rating r ON tbi.id = r.taxBeneficiaryId AND r.userId = :userId AND r.billingPeriod = :billingPeriod")
    List<TaxBeneficiaryIndividual> getAllTaxBeneficiariesIndividuals(String userId, String billingPeriod);

}
