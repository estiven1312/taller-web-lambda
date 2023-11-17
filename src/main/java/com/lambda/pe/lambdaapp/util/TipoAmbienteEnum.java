package com.lambda.pe.lambdaapp.util;

public enum TipoAmbienteEnum {
    SALA("SALA"),
    ESTACIONAMIENTO("ESTACIONAMIENTO"),

    CUBICULO("CUBICULO");
    private final String label;

    TipoAmbienteEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
