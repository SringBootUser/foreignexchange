package com.project.foreignexchange.controllers;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foreignexchange.dto.HistoricalDataDTO;
import com.project.foreignexchange.services.HistoricalRateService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HistoricalDataController {

	/*
	 * Test sample:
	 * http://localhost:8888/history?date=2019-03-07&currencies=USD,AUD,CAD,PLN,MXN
	 */

	private HistoricalRateService service;
	
	@Autowired
	public HistoricalDataController(final HistoricalRateService service) {
		this.service = service;
	}

	@Operation(summary = "Read conversion history")
	@GetMapping("/history")
	public ResponseEntity<Map<String, BigDecimal>> getConversionHistory(HistoricalDataDTO historicalData) {
		Map<String, BigDecimal> historicalDataResponse = this.service.extractHistoricalData(historicalData);
		return ResponseEntity.status(HttpStatus.OK).body(historicalDataResponse);
	}
}
