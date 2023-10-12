package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.CryptoPriceDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.CryptoPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CryptoPriceConverter {
    CryptoPriceDto convertTo(CryptoPrice cryptoPrice);
}
