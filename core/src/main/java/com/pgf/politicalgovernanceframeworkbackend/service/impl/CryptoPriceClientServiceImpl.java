package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgf.politicalgovernanceframeworkbackend.service.CryptoPriceClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.pgf.politicalgovernanceframeworkbackend.utils.Constants.COINAPI_API_BASE_URL;
import static com.pgf.politicalgovernanceframeworkbackend.utils.Constants.X_COINAPI_KEY;

@Service
public class CryptoPriceClientServiceImpl implements CryptoPriceClientService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${coinapi-key}")
    private String API_KEY;

    public CryptoPriceClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getEthUsdPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_COINAPI_KEY, API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                COINAPI_API_BASE_URL + "/exchangerate/ETH/USD",
                HttpMethod.GET,
                entity,
                String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("rate").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getEthPlnPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_COINAPI_KEY, API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                COINAPI_API_BASE_URL + "/exchangerate/ETH/PLN",
                HttpMethod.GET,
                entity,
                String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("rate").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
