package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
