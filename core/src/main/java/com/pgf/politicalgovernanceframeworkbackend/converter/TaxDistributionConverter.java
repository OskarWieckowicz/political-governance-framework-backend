package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.TaxDistribution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxDistributionConverter {
    TaxDistribution convertFrom(TaxDistributionDto source);
}
