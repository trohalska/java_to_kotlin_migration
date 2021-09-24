package com.example.model;

public enum SimpleOperation {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String sign;

    SimpleOperation(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
