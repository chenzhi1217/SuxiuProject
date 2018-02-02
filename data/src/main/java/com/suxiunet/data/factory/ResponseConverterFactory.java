package com.suxiunet.data.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.suxiunet.data.converter.ResponseConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author : chenzhi
 * time   : 2018/02/02
 * desc   : 相应体转换器
 */
public class ResponseConverterFactory extends Converter.Factory {
    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);
    }
    
    private final Gson gson;
    
    private ResponseConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ResponseConverter<>(gson, adapter, type);
    }
    
}

