package com.suxiunet.data.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.DrawableRes;
import android.view.WindowManager;
import android.widget.RadioButton;

/**
 * author : chenzhi
 * time   : 2017/12/12
 * desc   :
 */
public class Utils {
    /**
     * 设置RadioButton图片的大小
     * @param context      上下文
     * @param idRes        图片资源id
     * @param radioButton  radioButton控件
     * @param size         图片的大小  单位dp
     */
    public static void setRadioButtonSize(Context context , @DrawableRes int idRes, RadioButton radioButton, int size) {
        if(radioButton == null) return;
        Drawable drawable = context.getApplicationContext().getResources().getDrawable(idRes);
        drawable.setBounds(0, 0, SizeUtils.dp2px(context.getApplicationContext(), size), SizeUtils.dp2px(context.getApplicationContext(), size));
        radioButton.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 网络是否可用：可用，但是不保证一定连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnectedOrConnecting(Context context) {
        // 判断网络不是出于连接或者正在连接的状态：
        // =======================
        final ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            return false ;
        }

        return true;
    }

    /**
     * 禁止一进Activity就弹出软键盘
     *
     * @param activity
     */
    public static void notAllowSoftInput(Activity activity) {
        //禁止默认弹出软键盘
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
