package com.project.foreignexchange.dto;

import java.util.List;

public class HistoricalDataDTO {
	String date;
	String source;
	List<String> currencies;
	
	public HistoricalDataDTO() {
		super();
	}

	public HistoricalDataDTO(String date, String source, List<String> currencies) {
		super();
		this.date = date;
		this.source = source;
		this.currencies = currencies;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public List<String> getCurrencies() {
		return currencies;
	}
	
	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}
	
}
