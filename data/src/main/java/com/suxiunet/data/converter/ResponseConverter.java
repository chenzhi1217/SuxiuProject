package com.suxiunet.data.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.exception.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author : chenzhi
 * time   : 2018/02/02
 * desc   :
 */
public class ResponseConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = "ResponseConverter";
    private final Gson gson;
    private final Type type;
    private TypeAdapter<T> adapter;

    public ResponseConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }
    
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        T data = gson.fromJson(response, type);
        if (data == null) {
            throw new JsonSyntaxException("数据解析错误");
        }
        if (data instanceof ApiResponse){
            ApiResponse result = (ApiResponse) data;
            int code = result.getRetCode();
            if (code == 405) {
                throw new ApiException(result.getRetMsg(), code);
            }
        }
        return data;
    }
}
