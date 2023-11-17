package com.lambda.pe.lambdaapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public @Data class FileConfig {
    @Value("${app.storage.path}")
    private String STORAGEPATH;
}
