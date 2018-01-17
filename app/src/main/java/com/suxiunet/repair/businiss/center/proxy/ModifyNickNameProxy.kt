package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.UserInfoRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/17
 * desc   :
 */
class ModifyNickNameProxy: RefreshProxy<UserInfoRequest,Any> {
    
    lateinit var mUserApiImpl: UserApiImpl
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    
    override fun getObservable(request: UserInfoRequest): Observable<Any> {
        return mUserApiImpl.modifyNickName(request.loginId,request.nickName)
                .map(object : Func1<ApiResponse<Any>,Any>{
                    override fun call(t: ApiResponse<Any>?): Any {
                        return t?.retData?:Any()
                    }
                })
    }
}