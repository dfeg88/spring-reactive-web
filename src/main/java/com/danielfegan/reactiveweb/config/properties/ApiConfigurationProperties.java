package com.danielfegan.reactiveweb.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class ApiConfigurationProperties {
    private String restCountriesBaseUrl;
}
