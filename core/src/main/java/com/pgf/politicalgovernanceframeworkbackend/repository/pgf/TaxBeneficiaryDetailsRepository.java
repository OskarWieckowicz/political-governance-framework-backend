package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryDetails;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBeneficiaryDetailsRepository extends JpaRepository<TaxBeneficiaryDetails, UUID> {
    Optional<TaxBeneficiaryDetails> findByName(String name);
}