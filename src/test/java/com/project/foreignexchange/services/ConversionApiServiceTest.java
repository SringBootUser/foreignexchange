package com.project.foreignexchange.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.foreignexchange.clients.ConversionApiClient;
import com.project.foreignexchange.controllers.TestConfiguration;
import com.project.foreignexchange.dto.ConversionDTO;
import com.project.foreignexchange.util.ApiUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
class ConversionApiServiceTest {

    @Autowired
    private ConversionApiService conversionApiService;

    @MockBean
    private ConversionApiClient conversionApiClient;

    @Test
    void testExtractConversionResult() {
        // Mock response from conversion API client
        String jsonResponse = "{\"result\": 125.50}";
        Mockito.when(conversionApiClient.getConversionResponse(ApiUtils.API_TOKEN, "USD", "GBP", "100"))
                .thenReturn(jsonResponse);

        // Call the method under test
        ConversionDTO conversion = new ConversionDTO("USD", "GBP", "100");
        Map<String, Object> conversionResult = conversionApiService.extractConversionResult(conversion);

        // Verify the result
        BigDecimal expectedConvertedAmount = new BigDecimal("125.50");
        UUID transactionGuid = UUID.fromString((String) conversionResult.get("transactionId"));

        assertEquals(expectedConvertedAmount, conversionResult.get("convertedAmount"));
        assertEquals(transactionGuid.toString(), conversionResult.get("transactionId"));
    }
}