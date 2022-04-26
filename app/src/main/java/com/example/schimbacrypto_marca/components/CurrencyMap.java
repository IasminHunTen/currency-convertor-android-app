

package com.example.schimbacrypto_marca.components;



import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class CurrencyMap {
    private Map<String, Currency> currencyList;

    public CurrencyMap(Activity activity) {
        currencyList = new HashMap<>();
        currencyList.put("USD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_usd", null, activity.getPackageName()),
                "USD"));
        currencyList.put("FJD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_fjd", null, activity.getPackageName()),
                "FJD"));
        currencyList.put("HKD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_hkd", null, activity.getPackageName()),
                "HKD"));
        currencyList.put("BRL", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_brl", null, activity.getPackageName()),
                "BRL"));
        currencyList.put("VND", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_vnd", null, activity.getPackageName()),
                "VND"));
        currencyList.put("ZAR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_zar", null, activity.getPackageName()),
                "ZAR"));
        currencyList.put("KES", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_kes", null, activity.getPackageName()),
                "KES"));
        currencyList.put("UAH", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_uah", null, activity.getPackageName()),
                "UAH"));
        currencyList.put("BWP", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_bwp", null, activity.getPackageName()),
                "BWP"));
        currencyList.put("KRW", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_krw", null, activity.getPackageName()),
                "KRW"));
        currencyList.put("CLP", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_clp", null, activity.getPackageName()),
                "CLP"));
        currencyList.put("SGD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_sgd", null, activity.getPackageName()),
                "SGD"));
        currencyList.put("GBP", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_gbp", null, activity.getPackageName()),
                "GBP"));
        currencyList.put("NOK", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_nok", null, activity.getPackageName()),
                "NOK"));
        currencyList.put("AUD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_aud", null, activity.getPackageName()),
                "AUD"));
        currencyList.put("IDR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_idr", null, activity.getPackageName()),
                "IDR"));
        currencyList.put("PEN", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_pen", null, activity.getPackageName()),
                "PEN"));
        currencyList.put("MYR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_myr", null, activity.getPackageName()),
                "MYR"));
        currencyList.put("PHP", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_php", null, activity.getPackageName()),
                "PHP"));
        currencyList.put("AED", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_aed", null, activity.getPackageName()),
                "AED"));
        currencyList.put("ILS", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ils", null, activity.getPackageName()),
                "ILS"));
        currencyList.put("CZK", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_czk", null, activity.getPackageName()),
                "CZK"));
        currencyList.put("GEL", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_gel", null, activity.getPackageName()),
                "GEL"));
        currencyList.put("UGX", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ugx", null, activity.getPackageName()),
                "UGX"));
        currencyList.put("GHS", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ghs", null, activity.getPackageName()),
                "GHS"));
        currencyList.put("EGP", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_egp", null, activity.getPackageName()),
                "EGP"));
        currencyList.put("PKR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_pkr", null, activity.getPackageName()),
                "PKR"));
        currencyList.put("THB", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_thb", null, activity.getPackageName()),
                "THB"));
        currencyList.put("ARS", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ars", null, activity.getPackageName()),
                "ARS"));
        currencyList.put("RON", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ron", null, activity.getPackageName()),
                "RON"));
        currencyList.put("HRK", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_hrk", null, activity.getPackageName()),
                "HRK"));
        currencyList.put("DKK", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_dkk", null, activity.getPackageName()),
                "DKK"));
        currencyList.put("MAD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_mad", null, activity.getPackageName()),
                "MAD"));
        currencyList.put("LKR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_lkr", null, activity.getPackageName()),
                "LKR"));
        currencyList.put("INR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_inr", null, activity.getPackageName()),
                "INR"));
        currencyList.put("RUB", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_rub", null, activity.getPackageName()),
                "RUB"));
        currencyList.put("SEK", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_sek", null, activity.getPackageName()),
                "SEK"));
        currencyList.put("CHF", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_chf", null, activity.getPackageName()),
                "CHF"));
        currencyList.put("BDT", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_bdt", null, activity.getPackageName()),
                "BDT"));
        currencyList.put("CNY", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_cny", null, activity.getPackageName()),
                "CNY"));
        currencyList.put("BGN", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_bgn", null, activity.getPackageName()),
                "BGN"));
        currencyList.put("PLN", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_pln", null, activity.getPackageName()),
                "PLN"));
        currencyList.put("NGN", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_ngn", null, activity.getPackageName()),
                "NGN"));
        currencyList.put("NPR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_npr", null, activity.getPackageName()),
                "NPR"));
        currencyList.put("CAD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_cad", null, activity.getPackageName()),
                "CAD"));
        currencyList.put("JPY", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_jpy", null, activity.getPackageName()),
                "JPY"));
        currencyList.put("HUF", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_huf", null, activity.getPackageName()),
                "HUF"));
        currencyList.put("TZS", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_tzs", null, activity.getPackageName()),
                "TZS"));
        currencyList.put("EUR", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_eur", null, activity.getPackageName()),
                "EUR"));
        currencyList.put("NZD", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_nzd", null, activity.getPackageName()),
                "NZD"));
        currencyList.put("MXN", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_mxn", null, activity.getPackageName()),
                "MXN"));
        currencyList.put("XOF", new Currency(
                activity.getResources().getIdentifier(
                        "@drawable/ic_xof", null, activity.getPackageName()),
                "XOF"));
    }

    public void updateCurrenciesRate(JSONObject jsonObject) {

        try{
            JSONObject currenciesRate = jsonObject.getJSONObject("results");
            for(Map.Entry<String, Currency> entry: currencyList.entrySet())
                entry.getValue().setRapport_with_base_currency(currenciesRate.getDouble(entry.getKey()));
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
    }

    public ArrayList<Currency> getOnlyTheValues(){
        ArrayList<Currency> values = new ArrayList<>();
        for (Map.Entry<String, Currency> entry: currencyList.entrySet())
            values.add(entry.getValue());
        return values;
    }

    public Currency getCurrencyByKey(String key) {
        return currencyList.get(key);
    }

    public Currency getRandomCurrency(){
       Random rd = new Random();
       ArrayList<Currency> list = getOnlyTheValues();
       return list.get(rd.nextInt(list.size()));
    }
    @Override
    public String toString() {
        return "CurrencyList{" +
                "currencyList=" + currencyList +
                '}';
    }

}