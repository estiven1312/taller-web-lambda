package com.lambda.pe.lambdaapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data

public class WsConfig {
    @Value("${ws.validation-image.url}")
    private String wsRoot;


}