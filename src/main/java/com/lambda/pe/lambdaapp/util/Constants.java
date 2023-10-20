package com.lambda.pe.lambdaapp.util;

public enum Constants {
    USER_KEY_SESSION("userSession"),
    FLAG_SESSION("activeSession");

    public final String label;

    Constants(String label) {
        this.label = label;
    }
}
