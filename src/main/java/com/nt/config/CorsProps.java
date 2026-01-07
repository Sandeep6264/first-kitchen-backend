package com.nt.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "app.cors")

public class CorsProps {
    private List<String> allowedOrigins;

	public List<String> getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	@Override
	public String toString() {
		return "CorsProps [allowedOrigins=" + allowedOrigins + "]";
	}

	public CorsProps(List<String> allowedOrigins) {
		super();
		this.allowedOrigins = allowedOrigins;
	}

	public CorsProps() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}