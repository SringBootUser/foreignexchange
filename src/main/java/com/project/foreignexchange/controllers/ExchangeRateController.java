package com.project.foreignexchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.foreignexchange.domain.ExchangeResponse;
import com.project.foreignexchange.dto.ExchangeDTO;
import com.project.foreignexchange.services.ExchangeRateService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ExchangeRateController {

	/*
	 * Test sample: 
		curl --location --request POST 'http://localhost:8888/exchange' \
		--header 'Content-Type: application/json' \
		--data-raw '{
		    "source": "USD",
		    "target": "GBP"
		}'
	 */

	private ExchangeRateService service;

	@Autowired
	public ExchangeRateController(final ExchangeRateService service) {
		this.service = service;
	}

	@Operation(summary = "Add exchange rate")
	@PostMapping("/exchange")
    public ResponseEntity<ExchangeResponse> addExchangeRate(@RequestBody ExchangeDTO exchangeRequest) {
        ExchangeResponse exchangeResponse = this.service.assembleExchangeResponse(exchangeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeResponse);
    }
}