package com.lambda.pe.lambdaapp.util;

public enum CatalogoEstadosIncidenciaEnum {
    ESTADO_REGISTRADO("REGISTRADO"),
    ESTADO_EN_PROCESO("PROCESO"),

    ESTADO_RESUELTO("RESUELTO");
    private final String label;

    CatalogoEstadosIncidenciaEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
