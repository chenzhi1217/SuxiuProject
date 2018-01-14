package com.suxiunet.data.entity.user;

import com.suxiunet.data.entity.base.BasicDataEntity;

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 用户信息
 */
public class UserInfoEntity extends BasicDataEntity {

    /**
     * token : eff15c18291ac26f11212a2e9b645fe9
     */
    private String token;
    /**
     * 电话号码
     */
    private String loginId;
    /**
     * 用户昵称
     */
    private String loginName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 用户头像url
     */
    private String url;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
