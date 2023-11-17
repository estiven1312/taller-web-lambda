package com.lambda.pe.lambdaapp.domain.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

public @Data class Response {
    private String message;
    private String id;
    private HttpStatus httpStatus;
}
