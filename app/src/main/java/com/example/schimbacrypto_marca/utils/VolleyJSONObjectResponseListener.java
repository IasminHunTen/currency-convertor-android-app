package com.example.schimbacrypto_marca.utils;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyJSONObjectResponseListener {
    void onResponse(JSONObject response) throws JSONException;
    void onError(String errors);
}
