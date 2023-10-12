package com.pgf.politicalgovernanceframeworkbackend.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CryptoPriceClientService {
    private final String COINAPI_API_BASE_URL = "https://rest.coinapi.io/v1";

    @Value("${coinapi-key}")
    private String API_KEY; // Replace with your CoinAPI key
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Double getEthUsdPrice() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COINAPI_API_BASE_URL + "/exchangerate/ETH/USD");
            httpGet.setHeader("X-CoinAPI-Key", API_KEY);
            HttpResponse response = httpClient.execute(httpGet);

            JsonNode jsonNode = objectMapper.readTree(response.getEntity().getContent());
            return jsonNode.get("rate").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getEthPlnPrice() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COINAPI_API_BASE_URL + "/exchangerate/ETH/PLN");
            httpGet.setHeader("X-CoinAPI-Key", API_KEY);
            HttpResponse response = httpClient.execute(httpGet);

            JsonNode jsonNode = objectMapper.readTree(response.getEntity().getContent());
            return jsonNode.get("rate").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

