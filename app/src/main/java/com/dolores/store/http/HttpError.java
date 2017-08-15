package com.dolores.store.http;

import com.android.volley.VolleyError;

public class HttpError extends VolleyError {

    private int resultCode;

    public HttpError(int resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

}
