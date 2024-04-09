package com.project.foreignexchange.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.foreignexchange.clients.ExchangeClient;
import com.project.foreignexchange.domain.ExchangeResponse;
import com.project.foreignexchange.dto.ExchangeDTO;
import com.project.foreignexchange.repository.ExchangeRepository;
import com.project.foreignexchange.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private ExchangeRepository repository;

    private final Gson gson = new Gson();

    public ExchangeResponse assembleExchangeResponse(ExchangeDTO exchangeRequest) {
        try {
            String jsonResponse = getApiResponseExchange(exchangeRequest.getSource(), exchangeRequest.getTarget());

            if (jsonResponse == null || jsonResponse.isEmpty()) {
                return new ExchangeResponse();
            }

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            String source = jsonObject.get("source").getAsString();
            JsonObject quotesJson = jsonObject.getAsJsonObject("quotes");
            Map<String, BigDecimal> quotes = gson.fromJson(quotesJson, Map.class);

            ExchangeResponse exchangeResponse = new ExchangeResponse();
            exchangeResponse.setSource(source);
            exchangeResponse.setQuotes(convertQuotesToString(quotes));

            saveExchangeResponse(exchangeResponse);

            return exchangeResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ExchangeResponse();
    }

    private String getApiResponseExchange(String source, String target) {
        return exchangeClient.getExchangeRates(ApiUtils.API_TOKEN,
                target.toUpperCase(), source.toUpperCase());
    }

    private void saveExchangeResponse(ExchangeResponse exchangeResponse) {
        repository.save(exchangeResponse);
    }

    private String convertQuotesToString(Map<String, BigDecimal> quotes) {
        return gson.toJson(quotes);
    }
}