package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.PaymentReceivedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentReceivedEventRepository extends JpaRepository<PaymentReceivedEvent, UUID> {
    Optional<PaymentReceivedEvent> findTopByOrderByIdDesc();
}
