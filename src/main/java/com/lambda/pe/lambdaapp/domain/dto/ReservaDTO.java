package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;

public @Data class ReservaDTO {
    private String date;
    private Long idReserva;
    private String initHour;
    private String endHour;
    private Long idAmbiente;
}
