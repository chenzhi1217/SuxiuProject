package com.suxiunet.repair.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author : chenzhi
 * time   : 2018/02/08
 * desc   : 网络信息的工具类
 */
public class NetWorkUtil {

    private NetWorkUtil() {
    }
    
    private static NetWorkUtil mInstance;

    public static NetWorkUtil getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkUtil.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 判断当前网络是否可用
     * @param context
     * @return
     */
    public boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
