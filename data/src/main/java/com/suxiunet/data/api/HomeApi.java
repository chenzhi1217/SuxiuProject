package com.suxiunet.data.api;
import retrofit2.http.GET;
import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页模块的api
 */
public interface HomeApi {
    @GET("/v2/home")
    Observable<Object> fetchHomeData();
}
