package com.suxiunet.data.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 月光和我 on 2017/2/3.
 */
public class SpUtil {
    
    private static final String NAME = "config";
    private static SharedPreferences mSp;

    public static final String TOKEN_KEY = "token_key";
    public static final String LOGIN_ID_KEY = "login_id_key";

    /**
     * 向SP中保存String类型数据
     * @param mContext
     * @param key
     * @param value
     */
    public static void putString(Context mContext, String key, String value) {
        Context applicationContext =  mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putString(key, value).commit();
    }

    /**
     * 从SP中获取String类型数据
     * @param mContext
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context mContext, String key, String defValue) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return mSp.getString(key, defValue);
    }



    /**
     * 向SP中保存int类型数据
     * @param mContext
     * @param key
     * @param value
     */
    public static void putInt(Context mContext, String key, int value) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putInt(key, value).commit();
    }

    /**
     * 从SP中获取int类型数据
     * @param mContext
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context mContext, String key, int defValue) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return mSp.getInt(key, defValue);
    }



    /**
     * 向SP中保存boolean类型数据
     * @param mContext
     * @param key
     * @param value
     */
    public static void putBoolean(Context mContext, String key, boolean value) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putBoolean(key, value).commit();
    }

    /**
     * 从SP中获取boolean类型数据
     * @param mContext
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return mSp.getBoolean(key, defValue);
    }

    /***
     * 删除单个
     * @param mContext
     * @param key
     */
    public static void deleShare(Context mContext, String key) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().remove(key).commit();
    }


    /**
     * 删除全部
     * @param mContext
     */
    public static void deleAll(Context mContext) {
        Context applicationContext = mContext.getApplicationContext();
        if (mSp == null) {
            mSp = applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().clear().commit();
    }
}
