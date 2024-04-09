package com.project.foreignexchange.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foreignexchange.dto.ConversionDTO;
import com.project.foreignexchange.services.ConversionApiService;

@RestController
public class ConversionApiController {

	/*
	 * Test sample: http://localhost:8888/convert?source=USD&target=PLN&amount=100
	 * 
	 */

	private ConversionApiService service;

	@Autowired
	public ConversionApiController(final ConversionApiService service) {
		this.service = service;
	}

	@GetMapping("/convert")
	public ResponseEntity<Map<String, Object>> conversionRate(ConversionDTO conversion) {
		Map<String, Object> conversionResult = this.service.extractConversionResult(conversion);
		return ResponseEntity.status(HttpStatus.OK).body(conversionResult);
	}
}
