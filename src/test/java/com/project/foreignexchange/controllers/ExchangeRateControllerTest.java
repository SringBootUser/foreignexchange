package com.project.foreignexchange.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import com.project.foreignexchange.domain.ExchangeResponse;
import com.project.foreignexchange.dto.ExchangeDTO;
import com.project.foreignexchange.services.ExchangeRateService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
@AutoConfigureMockMvc
public class ExchangeRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Test
    public void addExchangeRate_Success() throws Exception {
        // Mock the behavior of the service
        ExchangeResponse exchangeResponse = new ExchangeResponse(1, "USD", "GBP");
        when(exchangeRateService.assembleExchangeResponse(any(ExchangeDTO.class))).thenReturn(exchangeResponse);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"source\": \"USD\", \"target\": \"GBP\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.source").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quotes").value("GBP"))
                .andReturn();
    }
}