package com.suxiunet.data.factory;

import android.content.Context;
import android.support.annotation.NonNull;

import com.suxiunet.data.intercepter.ApiInterceptor;
import com.suxiunet.data.intercepter.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   : 接口实例的工厂类
 */
public class RetrofitFactory {

    public static <T> T creat(Class<T> clazz, @NonNull Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new ApiInterceptor())
                .addInterceptor(new LogInterceptor())
                .build();
    
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://119.28.52.119:80/")
                .build();
        return retrofit.create(clazz);
    }
}
