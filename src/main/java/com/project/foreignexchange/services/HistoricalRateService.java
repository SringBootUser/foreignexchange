package com.project.foreignexchange.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.foreignexchange.clients.HistoricalDataClient;
import com.project.foreignexchange.dto.HistoricalDataDTO;
import com.project.foreignexchange.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class HistoricalRateService {

    @Autowired
    private HistoricalDataClient historicalDataClient;

    private final Gson gson = new Gson();

    public Map<String, BigDecimal> extractHistoricalData(HistoricalDataDTO historicalData) {
        String jsonResponse = getHistoricalData(historicalData);
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, BigDecimal> quotes = new HashMap<>();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonObject quotesJson = jsonObject.getAsJsonObject("quotes");

        for (Map.Entry<String, JsonElement> entry : quotesJson.entrySet()) {
            BigDecimal value = entry.getValue().getAsBigDecimal();
            quotes.put(entry.getKey(), value);
        }

        return quotes;
    }

    private String getHistoricalData(HistoricalDataDTO historicalData) {
        // Call Feign client method to get API response
        return historicalDataClient.getHistoricalRates(ApiUtils.API_TOKEN, historicalData.getDate(),
                historicalData.getSource(), historicalData.getCurrencies());
    }
}