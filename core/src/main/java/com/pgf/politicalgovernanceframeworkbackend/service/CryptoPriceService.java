package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.CryptoPriceDto;

public interface CryptoPriceService {
    CryptoPriceDto getEthPrice();
    void updateEthPrice();
}
