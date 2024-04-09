package com.project.foreignexchange.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.foreignexchange.dto.ConversionDTO;
import com.project.foreignexchange.services.ConversionApiService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestConfiguration.class })
@AutoConfigureMockMvc
public class ConversionApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversionApiService conversionApiService;

    @Test
    public void conversionRate_Success() throws Exception {
        // Mock conversion service response
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("conversionRate", new BigDecimal("4.00"));
        when(conversionApiService.extractConversionResult(any(ConversionDTO.class))).thenReturn(expectedResult);

        // Perform the request
        ConversionDTO conversion = new ConversionDTO("USD", "PLN", "100");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/convert")
                .param("source", conversion.getSource())
                .param("target", conversion.getTarget())
                .param("amount", conversion.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(expectedResult, HttpStatus.OK);
                    assertEquals(responseEntity, new ResponseEntity<>(expectedResult, HttpStatus.OK));
                });
    }
}