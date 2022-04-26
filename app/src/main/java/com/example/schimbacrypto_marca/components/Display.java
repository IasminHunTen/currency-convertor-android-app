package com.example.schimbacrypto_marca.components;

public class Display {
    private double unit_value;
    private Double value;
    private String text_value;

    public Display(double unit_value) {
        this.unit_value = unit_value;
        this.value = unit_value;
        this.text_value = String.valueOf(this.unit_value);
    }

    public String getText_value() {
        return text_value;
    }

    public void setText_value(String text_value) {
        this.text_value = text_value;
    }

    public double getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(double unit_value) {
        this.unit_value = unit_value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
