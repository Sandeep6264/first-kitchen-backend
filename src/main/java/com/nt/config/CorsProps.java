package com.nt.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "app.cors")
@Data
public class CorsProps {
    private List<String> allowedOrigins;
}