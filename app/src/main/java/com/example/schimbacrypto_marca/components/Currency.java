package com.example.schimbacrypto_marca.components;

import android.content.Context;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Currency implements Serializable {
    private int logo;
    private String code_3;
    private double rapport_with_base_currency;

    public Currency(int logo, String code_3, double rapport_with_base_currency) {
        this.logo = logo;
        this.code_3 = code_3;
        this.rapport_with_base_currency = rapport_with_base_currency;

    }

    public Currency(int logo, String code_3) {
        this.logo = logo;
        this.code_3 = code_3;
        this.rapport_with_base_currency = 1.0;
    }

    public int getLogo() {
        return logo;
    }

    public String getCode_3() {
        return code_3;
    }

    public double getRapport_with_base_currency() {
        return rapport_with_base_currency;
    }

    public void setRapport_with_base_currency(double rapport_with_base_currency) {
        this.rapport_with_base_currency = rapport_with_base_currency;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setCode_3(String code_3) {
        this.code_3 = code_3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code_3, currency.code_3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code_3);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "logo=" + logo +
                ", code_3='" + code_3 + '\'' +
                ", rapport_with_base_currency=" + rapport_with_base_currency +
                '}';
    }
}
