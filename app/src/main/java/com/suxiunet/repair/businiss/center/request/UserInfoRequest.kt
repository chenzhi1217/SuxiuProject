package com.suxiunet.repair.businiss.center.request

import com.suxiunet.repair.base.BasicRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
    
    var file: MultipartBody.Part? = null
    
    var map: Map<String, String>? = null
    
}