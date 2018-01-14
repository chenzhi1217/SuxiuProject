package com.suxiunet.repair.base.baseui;

import android.app.Application;

import com.suxiunet.data.DatasConstant;

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   :
 */
public class SuXiuApplication extends Application {
    public static SuXiuApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
//        DataConstant.Companion.setMApplication(this);
        DatasConstant.mApplication = this;
    }
}
