package com.suxiunet.data.intercepter;

import android.os.Build;

import com.suxiunet.data.DatasConstant;
import com.suxiunet.data.util.SpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : chenzhi
 * time   : 2017/12/06
 * desc   : 拦截器，在这里添加请求头参数
 */
public class ApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        //添加默认Http userAgent
        builder.removeHeader("User-Agent").addHeader("User-Agent", getUserAgent());
        //添加自定义参数
        HashMap<String, String> header = getAppInfo();
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return chain.proceed(builder.build());
    }
    
    private static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try { //TODO 未完
//                userAgent = WebSettings.getDefaultUserAgent(BillApplication.getAppContext());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        String s = sb.toString();
        if (!s.contains("Android")) {
            s += "Android";
        }
        return s;
    }

    public static HashMap<String, String> getAppInfo() {
        HashMap<String, String> info = new HashMap<>();
        /*if (UserModel.isUserLogin()) {
            info.put("Authorization", UserModel.getUesr().getToken());
        }*/
//        UserModel user = UserModel.getUser();
//        info.put("device-type", "1");
//        info.put("channel-id", ChannelUtil.appChannel(context));
//        info.put("device-id", getDeviceId(context));
//        info.put("device-name", getDeviceName());
        /*info.put("version-name", BuildConfig.VERSION_NAME);
        info.put("device-name", getDeviceName());
        info.put("X-user-id", "6");*/
        String token = SpUtil.getString(DatasConstant.mApplication, SpUtil.TOKEN_KEY, "");
        info.put("TOKEN", token);
        return info;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }
}
