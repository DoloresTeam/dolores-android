package com.dolores.store.http;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.dolores.store.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class StringRequest extends Request<String> {

    private Map<String, String> mMap;
    private Listener<String> mListener;

    public StringRequest(String url, Map<String, String> map, Listener<String> listener, ErrorListener errorListener) {
        super(Method.GET, Constants.BASE_URL + url, errorListener);
        super.setShouldCache(false);
        mListener = listener;
        mMap = map;
    }

    public StringRequest(String url, int requestType, Map<String, String> map, Listener<String> listener, ErrorListener errorListener) {
        super(requestType, Constants.BASE_URL + url, errorListener);
        super.setShouldCache(false);
        mListener = listener;
        mMap = map;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, "UTF-8");
            Log.v("tag", parsed);
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, null);
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        int statusCode = error.networkResponse.statusCode;
        if (statusCode == 401 || statusCode == 500) {
            String statusMsg;
            try {
                statusMsg = new String(error.networkResponse.data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                statusMsg = new String(error.networkResponse.data);
            }
            try {
                JSONObject jsonObject = new JSONObject(statusMsg);
                String msg = jsonObject.optString("message");
                if (!TextUtils.isEmpty(msg)) {
                    Response.error(new HttpError(statusCode, msg));
                } else {
                    Response.error(new VolleyError(statusMsg));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Response.error(new VolleyError(statusMsg));
            }

        } else {
            super.deliverError(error);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}