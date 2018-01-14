package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.repair.base.BasicRequest
import com.suxiunet.repair.base.RefreshProxy
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 退出登录
 */
class QuitLoginProxy: RefreshProxy<BasicRequest, Any>{
    private lateinit var mUserApiImpl: UserApiImpl
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    
    override fun getObservable(request: BasicRequest): Observable<Any> {
        return mUserApiImpl.quitLogin()
                .map(object : Func1<ApiResponse<Any>,Any>{
                    override fun call(t: ApiResponse<Any>?): Any {
                        return t?.retData?:Any()
                    }
                })
    }
}