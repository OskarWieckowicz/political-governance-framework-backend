package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class CryptoPriceClientServiceImplTests {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CryptoPriceClientServiceImpl service;


    @Test
    void shouldSuccessfullyGetEthUsdPrice() {
        Double expectedRate = 1000.0;
        given(restTemplate.getForObject(anyString(), eq(Double.class))).willReturn(expectedRate);

        Double result = service.getEthUsdPrice();

        assertThat(result).isEqualTo(expectedRate);

    }

    @Test
    void shouldSuccessfullyGetEthPlnPrice() {
        Double expectedRate = 1000.0;
        given(restTemplate.getForObject(anyString(), eq(Double.class))).willReturn(expectedRate);

        Double result = service.getEthPlnPrice();

        assertThat(result).isEqualTo(expectedRate);

    }
}
