package com.example.schimbacrypto_marca.components;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class Volatility implements Serializable {
    private double mean, min, max;
    private long scale;
    private ArrayList<Double> volatility_ist;

    public Volatility(){
        volatility_ist = new ArrayList<>();
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public long getScale() {
        return scale;
    }

    public void setScale(long scale) {
        this.scale = scale;
    }

    public ArrayList<Double> getVolatility_list() {
        return volatility_ist;
    }

    public void setVolatility_list(JSONArray jsonArray) {
        int len = jsonArray.length();
        try {
            for(int i=0;i<len;i++)
                volatility_ist.add((Double) jsonArray.get(i));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Volatility{" +
                "mean=" + mean +
                ", scale=" + scale +
                ", volatility_ist=" + volatility_ist +
                '}';
    }
}
