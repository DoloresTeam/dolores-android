package com.dolores.store.util;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.dolores.store.http.ErrorCode;
import com.dolores.store.http.HttpError;

public class HttpUtil {

    public static String checkErrorType(VolleyError error) {
        String str = "";
        if (error instanceof NoConnectionError) {
            str = ErrorCode.IS_NOT_NETWORK;
        } else if (error instanceof AuthFailureError) {
            str = ErrorCode.AUTH_FAILED;
        } else if (error instanceof TimeoutError) {
            str = ErrorCode.CONNECTION_TIMEOUT;
        } else if (error instanceof ParseError) {
            str = ErrorCode.PARSE_DATA_ERROR;
        } else if (error instanceof ServerError) {
            str = ErrorCode.SERVER_ERROR;
        } else if (error instanceof HttpError) {
            HttpError httpError = (HttpError) error;
            str = httpError.getMessage();
            if (TextUtils.isEmpty(str)) {
                str = ErrorCode.REQUEST_ERROR;
            }
        } else {
            str = ErrorCode.REQUEST_ERROR;
        }
        return str;
    }
}
