package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.SuggestionsRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/16
 * desc   : 意见反馈
 */
class SuggestionsProxy: RefreshProxy<SuggestionsRequest,Any>{
    lateinit var mUserApiImpl: UserApiImpl
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    
    override fun getObservable(request: SuggestionsRequest): Observable<Any> {
        return mUserApiImpl.submitSuggestions(request.suggestions,request.loginId)
                .map(object : Func1<ApiResponse<Any>,Any>{
                    override fun call(t: ApiResponse<Any>?): Any {
                        return t?.retData?:Any()
                    }
                })
    }
}