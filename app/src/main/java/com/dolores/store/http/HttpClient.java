package com.dolores.store.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClient {
    private static RequestQueue requestQueue;
    private final Context context;
    private static AtomicInteger counter = new AtomicInteger(0);
    private boolean useCache = false;

    private HttpClient(Context context) {
        this.context = context;
    }

    public synchronized static HttpClient newInstance(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext(), null);
        }
        return new HttpClient(context);
    }

    public void clearCache() {
        requestQueue.getCache().clear();
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public void addRequest(Request request) {
        String tag = getTagAndCount();
        request.setTag(tag);
        request.setShouldCache(useCache);
        requestQueue.add(request);
    }

    public void cancelRequest(Request request) {
        requestQueue.cancelAll(request.getTag());
    }

    private String mapToQueryString(Map<String, Object> params) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null || entry.getValue() instanceof File)
                    continue;
                encodedParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                encodedParams.append('&');
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: UTF-8", uee);
        }
    }

    private String getTagAndCount() {
        int num = counter.getAndIncrement();
        return "HttpRequest-" + num;
    }

}
