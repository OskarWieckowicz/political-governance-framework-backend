package com.pgf.politicalgovernanceframeworkbackend.repository.pgf;

import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryDetails;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBeneficiaryDetailsRepository extends JpaRepository<TaxBeneficiaryDetails, Long> {
    @Query("SELECT NEW com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxBeneficiaryDetails(" +
        "tbd.id, tbd.image, tbd.name, 0, " +
        "tbd.description, tbd.site, tbd.leader, tbd.smartContractAddress) " +
        "FROM TaxBeneficiaryDetails tbd " +
        "WHERE tbd.name = :name ")
    Optional<TaxBeneficiaryDetails> findByNameCustom(String name);

    @Query("SELECT tbd, COALESCE(AVG(r.value), 0) " +
        "FROM TaxBeneficiaryDetails tbd " +
        "LEFT JOIN Rating r ON tbd.id = r.taxBeneficiaryId " +
        "WHERE tbd.name = :name " +
        "GROUP BY tbd.id")
    List<Object[]> findByNameAndCalculateAverageRating(@Param("name") String name);



}
