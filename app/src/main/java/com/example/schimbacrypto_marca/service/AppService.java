package com.example.schimbacrypto_marca.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import com.example.schimbacrypto_marca.utils.RequestQueueSingleton;
import com.example.schimbacrypto_marca.utils.VolleyJSONObjectResponseListener;
import com.example.schimbacrypto_marca.utils.VolleyStringResponseListener;

import org.json.JSONException;
import org.json.JSONObject;


public class
AppService {

    private static final String API_KEY = "d7fc33baa4-7ecdeff4fd-r5es04";
    private RequestQueueSingleton rqs;

    public AppService(Context context){
        rqs = RequestQueueSingleton.getInstance(context);
    }

    public void getLocation(VolleyStringResponseListener volleyStringResponseListener){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://ipapi.co/currency/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyStringResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyStringResponseListener.onError(error.toString());
            }
        });
        rqs.addToRequestQueue(stringRequest);
    }

    public void updateCurrenciesRate(String base, VolleyJSONObjectResponseListener volleyJSONObjectResponseListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://api.fastforex.io/fetch-all?from="+base+"&api_key=" + API_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            volleyJSONObjectResponseListener.onResponse(response);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyJSONObjectResponseListener.onError(error.toString());
                    }
                }

        );
        rqs.addToRequestQueue(jsonObjectRequest);
    }

    public void getVolatility(String from, String to, VolleyJSONObjectResponseListener volleyJSONObjectResponseListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://history-of-currencies.herokuapp.com/" + from + "/" + to + "/" + API_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            volleyJSONObjectResponseListener.onResponse(response);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                },
        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyJSONObjectResponseListener.onError(error.toString());
                    }
                }
        );
        rqs.addToRequestQueue(jsonObjectRequest);
    }
}
