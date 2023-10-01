package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Declaration;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, UUID> {
    Optional<Declaration> findByBillingPeriod(String billingPeriod);
}
