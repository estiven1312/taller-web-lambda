package com.lambda.pe.lambdaapp.util;

public enum CatalogoEstadosEnum {
    ESTADO_ACTIVO("ACTIVO"),
    ESTADO_ELIMINADO("ELIMINADO");
    private final String label;

    CatalogoEstadosEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
