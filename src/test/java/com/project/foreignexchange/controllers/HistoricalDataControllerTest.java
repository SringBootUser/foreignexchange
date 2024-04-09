package com.project.foreignexchange.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.foreignexchange.dto.HistoricalDataDTO;
import com.project.foreignexchange.services.HistoricalRateService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
@AutoConfigureMockMvc
public class HistoricalDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoricalRateService historicalRateService;

    @Test
    public void getHistoricalData_Success() throws Exception {
        // Mock service response
        Map<String, BigDecimal> expectedResponse = new HashMap<>();
        expectedResponse.put("USD", BigDecimal.valueOf(1.0));
        expectedResponse.put("AUD", BigDecimal.valueOf(1.3));

        when(historicalRateService.extractHistoricalData(any(HistoricalDataDTO.class))).thenReturn(expectedResponse);

        // Perform GET request and verify response
        mockMvc.perform(MockMvcRequestBuilders.get("/history")
                .param("date", "2019-03-07")
                .param("currencies", "USD", "AUD", "CAD", "PLN", "MXN")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.USD").value(1.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.AUD").value(1.3));
    }
}