package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static com.pgf.politicalgovernanceframeworkbackend.utils.Constants.COINAPI_API_BASE_URL;

import com.pgf.politicalgovernanceframeworkbackend.service.CryptoPriceClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CryptoPriceClientServiceImpl implements CryptoPriceClientService {

    @Value("${coinapi-key}")
    private String API_KEY;
    private final RestTemplate restTemplate;

    @Override
    public Double getEthUsdPrice() {
        String url = COINAPI_API_BASE_URL + "/exchangerate/ETH/USD";
        try {
            return restTemplate.getForObject(url, Double.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double getEthPlnPrice() {
        String url = COINAPI_API_BASE_URL + "/exchangerate/ETH/PLN";
        try {
            return restTemplate.getForObject(url, Double.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

