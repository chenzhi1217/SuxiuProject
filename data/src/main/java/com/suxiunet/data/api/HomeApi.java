package com.suxiunet.data.api;
import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.user.HomeEntity;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页模块的api
 */
public interface HomeApi {
    @POST("/dnwx/app/banner/getSlidShowList")
    Observable<ApiResponse<HomeEntity>> fetchHomeData();
}
