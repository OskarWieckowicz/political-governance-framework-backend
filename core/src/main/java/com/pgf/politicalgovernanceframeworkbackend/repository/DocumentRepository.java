package com.pgf.politicalgovernanceframeworkbackend.repository;

import com.pgf.politicalgovernanceframeworkbackend.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
