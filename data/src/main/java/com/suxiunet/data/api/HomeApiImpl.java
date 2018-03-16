package com.suxiunet.data.api;

import android.content.Context;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.user.HomeEntity;
import com.suxiunet.data.factory.RetrofitFactory;

import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页模块接口的实例
 */
public class HomeApiImpl implements HomeApi {


    private final HomeApi mHomeApi;

    public HomeApiImpl(Context context) {
        mHomeApi = RetrofitFactory.creat(HomeApi.class, context);
    }

    @Override
    public Observable<ApiResponse<HomeEntity>> fetchHomeData() {
        return mHomeApi.fetchHomeData();
    }
}
