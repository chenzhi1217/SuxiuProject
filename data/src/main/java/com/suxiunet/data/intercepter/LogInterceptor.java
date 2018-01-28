package com.suxiunet.data.intercepter;

import com.suxiunet.data.exception.ApiException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : chenzhi
 * time   : 2017/12/06
 * desc   :
 */
public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        LogUtils.i(request.body());
        Response response = chain.proceed(chain.request());
        
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        
//        LogUtils.i(request.toString());
//        LogUtils.json(content);
        
        /*if (response.code() != 000) {
            throw new ApiException(response.message(), response.code());
        }*/
        
        if (response.body() != null) {
            // 深坑！
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(mediaType, content);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }
}
