package com.pgf.politicalgovernanceframeworkbackend.service;

import static com.pgf.politicalgovernanceframeworkbackend.utils.Constants.ETH_PLN;

import com.pgf.politicalgovernanceframeworkbackend.converter.CryptoPriceConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.CryptoPriceDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.CryptoPrice;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.CryptoPriceRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoPriceService {
    private final CryptoPriceRepository repository;
    private final CryptoPriceConverter converter;
    private final CryptoPriceClientService cryptoPriceClientService;

    public CryptoPriceDto getEthPrice() {
        CryptoPrice cryptoPrice =
            repository.findFirstBySymbol(ETH_PLN).orElseThrow(() -> new NotFoundException("Price not found"));
        return converter.convertTo(cryptoPrice);
    }

    public void updateEthPrice() {
        CryptoPrice cryptoPrice =
            repository.findFirstBySymbol(ETH_PLN).orElse(CryptoPrice.builder().symbol(ETH_PLN).build());
        Double ethPlnPrice = cryptoPriceClientService.getEthPlnPrice();
        if(Objects.nonNull(ethPlnPrice)) {
            cryptoPrice.setPrice(ethPlnPrice);
        }
        cryptoPrice.setTimestamp(LocalDateTime.now());
        repository.save(cryptoPrice);
    }
}
