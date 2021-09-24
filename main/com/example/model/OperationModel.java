package com.example.model;

public class OperationModel {
    
    private int a;
    private int b;
    private int c;

    public OperationModel() {
    }

    public OperationModel(int c) {
        this.c = c;
    }

    public OperationModel(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "OperationModel{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
