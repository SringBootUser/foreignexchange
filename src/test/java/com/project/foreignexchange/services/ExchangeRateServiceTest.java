package com.project.foreignexchange.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.foreignexchange.clients.ExchangeClient;
import com.project.foreignexchange.controllers.TestConfiguration;
import com.project.foreignexchange.domain.ExchangeResponse;
import com.project.foreignexchange.dto.ExchangeDTO;
import com.project.foreignexchange.repository.ExchangeRepository;
import com.project.foreignexchange.util.ApiUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
class ExchangeRateServiceTest {

    @MockBean
    private ExchangeClient exchangeClient;

    @MockBean
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    void testAssembleExchangeResponse() {
        // Mock the exchange client response
        String jsonResponse = "{\"source\":\"USD\",\"quotes\":{\"USDGBP\":1.4}}";
        when(exchangeClient.getExchangeRates(ApiUtils.API_TOKEN, "GBP", "USD")).thenReturn(jsonResponse);

        // Call the method under test
        ExchangeDTO exchangeRequest = new ExchangeDTO("USD", "GBP");
        ExchangeResponse exchangeResponse = exchangeRateService.assembleExchangeResponse(exchangeRequest);

        // Verify that the exchange response is created and saved correctly
        assertEquals("USD", exchangeResponse.getSource());
        assertEquals("{\"USDGBP\":1.4}", exchangeResponse.getQuotes());

        verify(exchangeRepository).save(exchangeResponse);
    }
}