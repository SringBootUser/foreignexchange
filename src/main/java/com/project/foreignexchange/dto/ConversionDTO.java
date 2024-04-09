package com.project.foreignexchange.dto;

public class ConversionDTO {
	String source;
	String target;
	String amount;
	
	public ConversionDTO() {
		super();
	}

	public ConversionDTO(String source, String target, String amount) {
		super();
		this.source = source;
		this.target = target;
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
