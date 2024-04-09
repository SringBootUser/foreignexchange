package com.project.foreignexchange.dto;

public class ExchangeDTO {
    private String source;
    private String target;
    
	public ExchangeDTO() {
		super();
	}

	public ExchangeDTO(String source, String target) {
		super();
		this.source = source;
		this.target = target;
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

    
}
