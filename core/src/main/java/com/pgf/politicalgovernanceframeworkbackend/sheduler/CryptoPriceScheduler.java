package com.pgf.politicalgovernanceframeworkbackend.sheduler;

import com.pgf.politicalgovernanceframeworkbackend.service.impl.CryptoPriceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class CryptoPriceScheduler {
    private final CryptoPriceServiceImpl cryptoPriceService;

    @Scheduled(fixedRate = 900000)
    void updateCryptoPrice() {
        cryptoPriceService.updateEthPrice();
    }
}
