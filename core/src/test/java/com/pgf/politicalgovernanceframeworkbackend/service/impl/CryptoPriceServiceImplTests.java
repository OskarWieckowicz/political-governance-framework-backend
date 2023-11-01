package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static com.pgf.politicalgovernanceframeworkbackend.utils.Constants.ETH_PLN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.pgf.politicalgovernanceframeworkbackend.converter.CryptoPriceConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.CryptoPriceDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.CryptoPrice;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.CryptoPriceRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.CryptoPriceClientService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CryptoPriceServiceImplTests {
    @Mock
    private CryptoPriceRepository cryptoPriceRepository;
    @Mock
    private CryptoPriceConverter cryptoPriceConverter;
    @Mock
    private CryptoPriceClientService cryptoPriceClientService;
    @InjectMocks
    private CryptoPriceServiceImpl cryptoPriceService;

    @Test
    void shouldSuccessfullyGetEthPrice() {
        // Given
        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setSymbol(ETH_PLN);
        CryptoPriceDto expectedResult = CryptoPriceDto.builder().build();

        given(cryptoPriceRepository.findFirstBySymbol(ETH_PLN)).willReturn(Optional.of(cryptoPrice));
        given(cryptoPriceConverter.convertTo(cryptoPrice)).willReturn(expectedResult);

        // When
        CryptoPriceDto result = cryptoPriceService.getEthPrice();

        // Then
        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void shouldThrowExceptionWhenCryptoPriceNotFound() {
        // Given
        given(cryptoPriceRepository.findFirstBySymbol(ETH_PLN)).willReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> cryptoPriceService.getEthPrice())
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Price not found");
    }

    @Test
    void shouldSuccessfullyUpdateEthPrice() {
        // Given
        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setSymbol(ETH_PLN);

        given(cryptoPriceClientService.getEthPlnPrice()).willReturn(1000.0);
        given(cryptoPriceRepository.findFirstBySymbol(ETH_PLN)).willReturn(Optional.of(cryptoPrice));

        // When
        cryptoPriceService.updateEthPrice();

        // Then
        assertThat(cryptoPrice.getPrice()).isEqualTo(1000.0);
        assertThat(cryptoPrice.getTimestamp()).isNotNull();
    }

    @Test
    void shouldNotUpdateEthPriceWhenNewPriceIsNull() {
        // Given
        given(cryptoPriceClientService.getEthPlnPrice()).willReturn(null);
        given(cryptoPriceRepository.findFirstBySymbol(ETH_PLN)).willReturn(Optional.empty());

        // When
        cryptoPriceService.updateEthPrice();

        // Then
        verify(cryptoPriceRepository, never()).save(any(CryptoPrice.class));
    }
}
