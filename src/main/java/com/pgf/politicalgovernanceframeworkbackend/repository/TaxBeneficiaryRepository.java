package com.pgf.politicalgovernanceframeworkbackend.repository;

import com.pgf.politicalgovernanceframeworkbackend.entity.TaxBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBeneficiaryRepository extends JpaRepository<TaxBeneficiary, Long> {
}
