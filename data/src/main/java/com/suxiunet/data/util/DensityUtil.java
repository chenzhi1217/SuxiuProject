package com.suxiunet.data.util;

import android.content.Context;

/**
 * Created by 月光和我 on 2017/2/6.
 * 单位转换工具类（dp2px px2dp sp2px px2sp）
 */
public class DensityUtil {

    private float density;

    /**
     * 根据屏幕密度将dp转换成px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        //获取屏幕密度
        float density = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue * density + 0.5f);
    }
    
    /**
     * 根据屏幕密度将px转换成dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float density = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * 将sp转换成px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        float scaledDensity = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity + 0.5f);
    }

    /**
     * 将px转换成sp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        float scaledDensity = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scaledDensity + 0.5f);
    }
}
