package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxesDistributionDeclaration;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesDistributionDeclarationRepository extends JpaRepository<TaxesDistributionDeclaration, Long> {
    Optional<TaxesDistributionDeclaration> findFirstByUserId(String userId);
}
