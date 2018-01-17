package com.suxiunet.data.api;

import android.content.Context;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.user.UserInfoEntity;
import com.suxiunet.data.factory.RetrofitFactory;

import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 用户模块
 */
public class UserApiImpl implements UserApi {

    private final UserApi mApi;

    public UserApiImpl(Context context) {
        mApi = RetrofitFactory.creat(UserApi.class, context);
    }

    /**
     * 用户登录
     * @param loginName 手机号码
     * @param loginType 要不过户类型
     * @param checkCode 短信验证码
     * @return
     */
    @Override
    public Observable<ApiResponse<UserInfoEntity>> login(String loginName, String loginType, String checkCode) {
        return mApi.login(loginName,loginType,checkCode);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Observable<ApiResponse<Object>> quitLogin() {
        return mApi.quitLogin();
    }

    /**
     * 意见返回
     * @param suggestions 意见详情
     * @param loginId     用户id
     * @return
     */
    @Override
    public Observable<ApiResponse<Object>> submitSuggestions(String suggestions, String loginId) {
        return mApi.submitSuggestions(suggestions,loginId);
    }

    /**
     * 修改昵称
     * @param name
     * @return
     */
    @Override
    public Observable<ApiResponse<Object>> modifyNickName(String loginId,String name) {
        return mApi.modifyNickName(loginId,name);
    }

    /**
     * 修改昵称
     * @param gender 1:男 2：女 3：保密
     * @return
     */
    @Override
    public Observable<ApiResponse<Object>> modifyGender(String loginId,String gender) {
        return mApi.modifyGender(loginId,gender);
    }
}
