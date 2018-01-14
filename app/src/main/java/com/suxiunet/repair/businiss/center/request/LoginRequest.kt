package com.suxiunet.repair.businiss.center.request

import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   :
 */
class LoginRequest : BasicRequest() {
    private var loginName = ""
    private var checkCode = ""

    fun setLoginName(loginName: String) {
        this.loginName = loginName
    }

    fun getLoginName(): String {
        return this.loginName
    }

    fun setCheckCode(checkCode: String) {
        this.checkCode = checkCode
    }
    
    fun getCheckCode(): String{
        return this.checkCode
    }
    
}