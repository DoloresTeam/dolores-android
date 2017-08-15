package com.dolores.store.util;

import android.content.Context;

import com.dolores.store.widget.DefineToast;

public class ToastUtils {

    private static DefineToast defineToast;

    public static void showToast(Context context, String string, int duration) {
        if (defineToast != null) {
            defineToast = null;
        }
        defineToast = new DefineToast(context, string, duration);
        defineToast.show();
    }

    public static void showToast(Context context, String string) {
        if (defineToast != null) {
            defineToast = null;
        }
        defineToast = new DefineToast(context, string, 1);
        defineToast.show();
    }

    public static void showToast(Context context, int stringId) {
        String text = context.getString(stringId);
        if (defineToast != null) {
            defineToast = null;
        }
        defineToast = new DefineToast(context, text, 1);
        defineToast.show();
    }
}
