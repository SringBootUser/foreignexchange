package com.project.foreignexchange.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.foreignexchange.clients.HistoricalDataClient;
import com.project.foreignexchange.controllers.TestConfiguration;
import com.project.foreignexchange.dto.HistoricalDataDTO;
import com.project.foreignexchange.util.ApiUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
class HistoricalRateServiceTest {
	
	@Autowired
	private HistoricalRateService historicalRateService;

    @MockBean
    private HistoricalDataClient historicalDataClient;

    @Test
    void testExtractHistoricalData() {
        // Mock response from historical data client
        String jsonResponse = "{\"quotes\": {\"USD\": 1.0, \"EUR\": 0.85}}";
        when(historicalDataClient.getHistoricalRates(ApiUtils.API_TOKEN, "2024-01-01", "USD", List.of("EUR")))
                .thenReturn(jsonResponse);

        // Prepare HistoricalData object
        HistoricalDataDTO historicalData = new HistoricalDataDTO("2024-01-01", "USD", List.of("EUR"));

        // Call the method under test
        Map<String, BigDecimal> quotes = historicalRateService.extractHistoricalData(historicalData);

        // Verify the result
        Map<String, BigDecimal> expectedQuotes = new HashMap<>();
        expectedQuotes.put("USD", BigDecimal.valueOf(1.0));
        expectedQuotes.put("EUR", BigDecimal.valueOf(0.85));

        assertEquals(expectedQuotes, quotes);
    }
}