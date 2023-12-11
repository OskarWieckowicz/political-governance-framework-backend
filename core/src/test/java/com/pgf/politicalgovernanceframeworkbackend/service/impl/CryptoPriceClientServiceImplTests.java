package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class CryptoPriceClientServiceImplTests {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CryptoPriceClientServiceImpl service;

    @Test
    void shouldSuccessfullyGetEthUsdPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CoinAPI-Key", "your-api-key");

        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"rate\":1000.0}", HttpStatus.OK);

        given(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(String.class)))
            .willReturn(responseEntity);

        Double result = service.getEthUsdPrice();

        assertThat(result).isEqualTo(1000.0);
    }

    @Test
    void shouldSuccessfullyGetEthPlnPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CoinAPI-Key", "your-api-key");

        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"rate\":1000.0}", HttpStatus.OK);

        given(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(String.class)))
            .willReturn(responseEntity);

        Double result = service.getEthPlnPrice();

        assertThat(result).isEqualTo(1000.0);
    }
}
