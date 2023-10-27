package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;

public @Data class ReservaSalaDTO {
    private String date;
    private String initHour;
    private String endHour;
    private Long idSala;
}
