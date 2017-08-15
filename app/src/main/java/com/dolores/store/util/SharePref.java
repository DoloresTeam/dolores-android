package com.dolores.store.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.dolores.store.DoloresApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharePref {

    private SharedPreferences mSharedPreferences = null;
    private Editor mEditor = null;
    private static SharePref sharePref = null;

    public synchronized static SharePref getInstance(String PREF_NAME, Context ctx) {
        if (sharePref != null) {
            return sharePref;
        } else {
            return new SharePref(PREF_NAME, ctx);
        }
    }

    public SharePref(String PREF_NAME, Context ctx) {
        mSharedPreferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    public String getSharePrefString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }


    public boolean getSharePrefBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }


    public int getSharePrefInteger(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }


    public long getSharePrefLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public float getSharePrefFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public boolean putSharePrefString(String key, String value) {
        mEditor.putString(key, value);
        return mEditor.commit();
    }

    public boolean putSharePrefBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        return mEditor.commit();
    }

    public boolean putSharePrefFloat(String key, float value) {
        mEditor.putFloat(key, value);
        return mEditor.commit();
    }

    public boolean putSharePrefLong(String key, long value) {
        mEditor.putLong(key, value);
        return mEditor.commit();
    }

    public boolean putSharePrefInteger(String key, int value) {
        mEditor.putInt(key, value);
        return mEditor.commit();
    }

    public boolean removeKey(String key) {
        mEditor.remove(key);
        return mEditor.commit();
    }

    public boolean removeKeyAsyn(String key) {
        mEditor.remove(key);
        return mEditor.commit();
    }

    public boolean clear() {
        mEditor.clear();
        return mEditor.commit();
    }

    public Object getObjectFromString(String key) {
        Object value = null;
        try {
            byte[] bytes = android.util.Base64.decode(
                    DoloresApplication.mSharePref.getSharePrefString(key, ""), android.util.Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream oisArray = new ObjectInputStream(bais);
            value = oisArray.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void putObjectToString(String key, Object value) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            String data = new String(android.util.Base64.encodeToString(baos.toByteArray(), android.util.Base64.DEFAULT));
            DoloresApplication.mSharePref.putSharePrefString(key, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

