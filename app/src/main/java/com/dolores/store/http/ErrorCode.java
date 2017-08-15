package com.dolores.store.http;

public class ErrorCode {

    public final static String CONNECTION_TIMEOUT = "网络连接超时";
    public final static String IS_NOT_NETWORK = "当前网络不可用,请检查网络!";
    public final static String PARSE_DATA_ERROR = "数据解析异常";
    public final static String SERVER_ERROR = "服务端异常";
    public final static String REQUEST_ERROR = "请求失败,请稍后再试";
    public final static String AUTH_FAILED = "登录信息已过期";
}
