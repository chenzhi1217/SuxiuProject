package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.OrderInfoRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/18
 * desc   : 下单proxy
 */
class PlaceOrderProxy: RefreshProxy<OrderInfoRequest,Any> {
    var mUserApiImpl: UserApiImpl
    constructor(context: Context):super(context){
        mUserApiImpl = UserApiImpl(context)
    }

    override fun getObservable(request: OrderInfoRequest): Observable<Any> {
        return mUserApiImpl.placeOrder(request.loginId,request.company,request.contacts,request.contactTel,request.appointmentTime,request.serviceMode, request.machineMode,request.machineType,request.companyAdr, request.desc)
                .map(object : Func1<ApiResponse<Any>,Any>{
                    override fun call(t: ApiResponse<Any>?): Any {
                        return t?.retData?:Any()
                    }
                })
    }
}