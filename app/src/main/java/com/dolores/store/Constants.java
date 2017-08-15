package com.dolores.store;

public class Constants {

    public static final String PACKAGE_NAME = "com.dolores.store";

    private static final String VALUE_ENVIRON_TEST = "http://www.dolores.store:3280/";
    private static final String VALUE_ENVIRON_RELEASE = "http://www.dolores.store:3280/";

    public static String BASE_URL = null;

    static {
        switch (BuildConfig.ENV_TYPE) {
            case 1:
                BASE_URL = Constants.VALUE_ENVIRON_TEST;
                break;
            case 2:
                BASE_URL = Constants.VALUE_ENVIRON_RELEASE;
                break;
        }
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static class RequestCode {
        public static final int MODIFY = 1;
    }

    public static String LOGIN_URL = "login";
    public static String PROFILE_URL = "api/v1/profile";
}
