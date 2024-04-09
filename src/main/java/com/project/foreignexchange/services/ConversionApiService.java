package com.project.foreignexchange.services;

import com.google.gson.Gson;
import com.project.foreignexchange.clients.ConversionApiClient;
import com.project.foreignexchange.domain.ConversionResponse;
import com.project.foreignexchange.dto.ConversionDTO;
import com.project.foreignexchange.repository.ConversionRepository;
import com.project.foreignexchange.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ConversionApiService {

    @Autowired
    private ConversionApiClient conversionApiClient;

    @Autowired
    private ConversionRepository repository;

    private final Gson gson = new Gson();

    public Map<String, Object> extractConversionResult(ConversionDTO conversion) {
        String jsonResponse = getApiResponseConversion(conversion.getSource(), conversion.getTarget(), conversion.getAmount());
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            return Collections.emptyMap();
        }
        ConversionResponse response = gson.fromJson(jsonResponse, ConversionResponse.class);
        BigDecimal convertedAmount = response.getResult();

        UUID transactionGuid = UUID.randomUUID();

        Map<String, Object> conversionResult = new HashMap<>();
        conversionResult.put("convertedAmount", convertedAmount);
        conversionResult.put("transactionId", transactionGuid.toString());

        ConversionResponse conversionResponse = createConversionResponse(convertedAmount.toString(), transactionGuid.toString());
        saveConversionResponse(conversionResponse);

        return conversionResult;
    }

    private void saveConversionResponse(ConversionResponse conversionResponse) {
        repository.save(conversionResponse);
    }

    private ConversionResponse createConversionResponse(String convertedAmount, String transactionId) {
        ConversionResponse response = new ConversionResponse();
        response.setConvertedAmount(convertedAmount);
        response.setTransactionId(transactionId);
        return response;
    }

    private String getApiResponseConversion(String source, String target, String amount) {
        // Call Feign client method to get API response
        return conversionApiClient.getConversionResponse(ApiUtils.API_TOKEN, source.toUpperCase(),
                target.toUpperCase(), amount.toUpperCase());
    }
}