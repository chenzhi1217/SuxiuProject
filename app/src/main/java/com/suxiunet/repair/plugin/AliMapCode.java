package com.suxiunet.repair.plugin;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Copyright (C), 2017/9/21 91账单
 * Author: chenzhi
 * Email: chenzhi@91zdan.com
 * Description: 高德地图定位
 * */

public class AliMapCode {
    //申明AMapLocationClient对象
    public static AMapLocationClient mLocationClient;

    public static AMapLocationClientOption mLocationClientOption = new AMapLocationClientOption();

    //申明定位回调监听器
    public static AMapLocationListener mapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功
                    if (locationChangeListener != null) {
                        if (aMapLocation.getLatitude() == 0 || aMapLocation.getLongitude() == 0) {
                            locationChangeListener.locationDataFail(0, "定位数据异常");
                        }

                        float speed = aMapLocation.getSpeed();
                        locationChangeListener.locationDataSuccess(String.valueOf(aMapLocation.getLatitude()), String.valueOf(aMapLocation.getLongitude()), aMapLocation.getProvince(), aMapLocation.getCity(), aMapLocation.getDistrict(), aMapLocation.getStreet() + aMapLocation.getStreetNum());
                    }
                } else {
                    //定位失败
                    if (locationChangeListener != null) {
                        locationChangeListener.locationDataFail(aMapLocation.getErrorCode(), aMapLocation.getErrorInfo());
                    }
                }
            }
        }
    };

    /**
     * 设置定位为单次定位 默认情况下为连续定位
     */
    public static void setOnceLocation() {
        if (mLocationClientOption != null) {
            mLocationClientOption.setOnceLocationLatest(true);
        }
    }

    /**
     * 设置连续定位的时间间隔，默认为2000毫秒
     *
     * @param time 定位的间隔时间
    * */
    public static void setContinuousLocation(long time) {
        if (mLocationClientOption != null) {
            mLocationClientOption.setInterval(time);
        }
    }


     /** 定位开启的入口
     *
     * @param context  上下文
     * @param listener 定位结果的回调接口实例*/
    public static void startLocation(Context context, LocationChangeListener listener) {
        locationChangeListener = listener;
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());

        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗定位模式。
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);

        //设置返回地址信息
        mLocationClientOption.setNeedAddress(true);

        //设置回调监听
        mLocationClient.setLocationListener(mapLocationListener);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationClientOption);

        //开启定位
        mLocationClient.startLocation();
    }

    public static LocationChangeListener locationChangeListener;

    public interface LocationChangeListener {

        
         /** 定位成功的回调方法
         *
         * @param latitude  维度
         * @param longitude 精度
         * @param province  省
         * @param city      市
         * @param district  区
         * @param street    街道
          * */
        void locationDataSuccess(String latitude, String longitude, String province, String city, String district, String street);
           

         /** 定位失败的回调方法
         *
         * @param errorCode 错误码
         * @param errorInfo 错误信息*/
        void locationDataFail(int errorCode, String errorInfo);
    }
}
