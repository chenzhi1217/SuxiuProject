package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.LoginRequest
import rx.Observable

/**
 * author : chenzhi
 * time   : 2018/02/02
 * desc   : 获取短信验证码
 */
class GetVeriCodeProxy : RefreshProxy<LoginRequest, Any> {
    var mUserApiImpl: UserApiImpl
    
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    override fun getObservable(request: LoginRequest): Observable<Any> {
        return mUserApiImpl.sendSms(request.getLoginName(),"0")
                .map{t -> t.retData}
    }
}