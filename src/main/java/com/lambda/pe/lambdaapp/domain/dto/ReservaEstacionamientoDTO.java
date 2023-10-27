package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;

@Data
public class ReservaEstacionamientoDTO {
    private String date;
    private String initHour;
    private String endHour;
    private Long idEstacionamiento;

}
