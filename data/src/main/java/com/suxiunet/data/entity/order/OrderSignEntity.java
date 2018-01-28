package com.suxiunet.data.entity.order;

import com.suxiunet.data.entity.base.BasicDataEntity;

/**
 * author : chenzhi
 * time   : 2018/01/25
 * desc   :
 */
public class OrderSignEntity extends BasicDataEntity {
    private String loginId;
    
    private String body;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
