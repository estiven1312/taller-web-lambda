package com.lambda.pe.lambdaapp.util;

public enum CatalogoEstadosUser {
    ESTADO_ACTIVO("USER_ACTIVE"),
    ESTADO_ELIMINADO("USER_DELETE"),

    ESTADO_BLOQUEADO("USER_BLOCK");
    private final String label;

    CatalogoEstadosUser(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
