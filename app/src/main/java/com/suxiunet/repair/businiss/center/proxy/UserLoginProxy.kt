package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.LoginRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   :
 */
class UserLoginProxy: RefreshProxy<LoginRequest, UserInfoEntity> {
    private var mUserApiImpl: UserApiImpl 
    
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    
    override fun getObservable(request: LoginRequest): Observable<UserInfoEntity> {
        return mUserApiImpl.login(request.getLoginName(),"C",request.getCheckCode())
                .map(object : Func1<ApiResponse<UserInfoEntity>,UserInfoEntity>{
                    override fun call(t: ApiResponse<UserInfoEntity>?): UserInfoEntity {
                        return t?.retData?:UserInfoEntity()
                    }
                })
    }
}