package com.suxiunet.data.api;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.user.UserInfoEntity;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 用户模块接口
 */
public interface UserApi {

    /**
     * 登录接口
     *
     * @param loginName 手机号码
     * @param loginType 要不过户类型
     * @param checkCode 短信验证码
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/user/login")
    Observable<ApiResponse<UserInfoEntity>> login(@Field("loginId") String loginName, @Field("loginType") String loginType, @Field("checkCode") String checkCode);
    
    /**
     * 退出登录
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/user/loginout")
    Observable<ApiResponse<Object>> quitLogin(@Field("loginId") String loginId,@Field("loginType") String loginType);

    /**
     * 意见返回
     *
     * @param suggestions
     * @param loginId
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/user/suggestions")
    Observable<ApiResponse<Object>> submitSuggestions(@Field("loginType") String loginType,@Field("suggestions") String suggestions, @Field("loginId") String loginId);

    /**
     * 修改昵称
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/user/modifyUserInfo")
    Observable<ApiResponse<Object>> modifyNickName(@Field("loginId") String loginId, @Field("loginName") String name);

    /**
     * 修改性别
     * @param gender 1:男 2：女 3：保密
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/user/modifyUserInfo")
    Observable<ApiResponse<Object>> modifyGender(@Field("loginId") String loginId, @Field("gender") String gender);

    /**
     * 下单接口
     *
     * @param contacts        联系人
     * @param contactTel      联系人电话
     * @param appointmentTime 预约上门时间
     * @param serviceMode     服务方式
     * @param machineType     机器型号
     * @param companyAdr      地址
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/order/order")
    Observable<ApiResponse<Object>> placeOrder(@Field("loginId") String loginId,@Field("company") String company,@Field("contacts") String contacts, @Field("contactTel") String contactTel, @Field("appointmentTime") String appointmentTime, @Field("serviceMode") String serviceMode, @Field("machineMode") String machineMode,@Field("machineType") String machineType, @Field("companyAdr") String companyAdr,@Field("faultDesc") String faultDesc);

    /**
     * 获取短信验证码
     * @param mobile 电话号码
     * @param smsMode 短信的类型
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/sms/sendSms")
    Observable<ApiResponse<Object>> sendSms(@Field("mobile") String mobile, @Field("smsMode") String smsMode);

    /**
     * 上传头像
     * @return
     */
    @Multipart
    @POST("/dnwx/app/userPic/changeHeadPortrait")
    Observable<ApiResponse<Object>> upLoadImage(@QueryMap Map<String,String> descMap, @Part MultipartBody.Part file);
    
}
