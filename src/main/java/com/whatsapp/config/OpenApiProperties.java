package com.whatsapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi")
@AllArgsConstructor
@Getter
public class OpenApiProperties {

    private final String projectTitle;
    private final String projectDescription;
    private final String projectVersion;
}