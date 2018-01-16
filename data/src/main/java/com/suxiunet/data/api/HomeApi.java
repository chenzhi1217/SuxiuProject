package com.suxiunet.data.api;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页模块的api
 */
public interface HomeApi {
    @POST("dnwx/app/user/loginout")
    Observable<Object> fetchHomeData();
}
