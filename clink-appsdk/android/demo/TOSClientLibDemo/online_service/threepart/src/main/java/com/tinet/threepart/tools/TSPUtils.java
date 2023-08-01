package com.tinet.threepart.tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * @创建者
 * @描述 sharedPreferences工具类(单例模式)
 */
public class TSPUtils {
    public static final String SP_NAME = "common";
    private static volatile TSPUtils mTSPUtils;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private TSPUtils(Context context) {
        this.context = context;
        sp = this.context.getSharedPreferences(SP_NAME, Context.MODE_APPEND);
        editor = sp.edit();
    }

    public static TSPUtils getInstance(Context context) {

        if (mTSPUtils == null) {
            synchronized (TSPUtils.class) {
                if (mTSPUtils == null) {
                    mTSPUtils = new TSPUtils(context.getApplicationContext());
                    return mTSPUtils;
                }
            }
        }

        return mTSPUtils;

    }

    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, Boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        if (key == null) {
            return;
        }
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public Long getLong(String key, Long defValue) {
        return sp.getLong(key, defValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putLong(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public void remove(String key) {
        sp.edit().remove(key).commit();
    }

}