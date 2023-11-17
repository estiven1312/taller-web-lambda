package com.lambda.pe.lambdaapp.util;

public enum CatalogoEnum {
    ESTADO_AUDITORIA("AUDITORIA"),
    ESTADO_RESERVA("RESERVA"),
    ESTADO_INCIDENCIA("INCIDENCIA"),
    TIPO_AMBIENTE("AMBIENTE");
    private final String label;

    CatalogoEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
