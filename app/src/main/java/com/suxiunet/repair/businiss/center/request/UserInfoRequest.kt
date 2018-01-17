package com.suxiunet.repair.businiss.center.request

import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/17
 * desc   : 用户信息的Request
 */
class UserInfoRequest : BasicRequest() {
    /**
     * 昵称
     */
    var nickName: String = ""

    /**
     * 性别
     */
    var gender: String = ""
    
    var loginId: String = ""
}