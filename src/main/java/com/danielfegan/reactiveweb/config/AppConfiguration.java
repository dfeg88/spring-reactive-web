package com.danielfegan.reactiveweb.config;

import com.danielfegan.reactiveweb.config.properties.ApiConfigurationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final ApiConfigurationProperties apiConfigurationProperties;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(apiConfigurationProperties.getRestCountriesBaseUrl()).build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return objectMapper;
    }

}

