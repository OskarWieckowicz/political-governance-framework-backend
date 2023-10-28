package com.pgf.politicalgovernanceframeworkbackend.entity.pgf;


import com.pgf.politicalgovernanceframeworkbackend.enums.TransactionTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private float amount;
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;
    private String fileName;
    private String key;
    private String userId;
}
