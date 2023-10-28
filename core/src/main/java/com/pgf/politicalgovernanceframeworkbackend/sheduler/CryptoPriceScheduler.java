package com.pgf.politicalgovernanceframeworkbackend.sheduler;

import com.pgf.politicalgovernanceframeworkbackend.service.CryptoPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoPriceScheduler {
    private final CryptoPriceService cryptoPriceService;

//    @Scheduled(fixedRate = 900000)
    void updateCryptoPrice() {
        cryptoPriceService.updateEthPrice();
        log.info("Cryptocurrency price updated.");
    }
}
