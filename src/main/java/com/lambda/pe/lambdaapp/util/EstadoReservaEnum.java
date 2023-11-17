package com.lambda.pe.lambdaapp.util;

public enum EstadoReservaEnum {
    VENCIDO("VENCIDO"),
    CANCELADO("CANCELADO"),

    VIGENTE("VIGENTE");
    private final String label;

    EstadoReservaEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
