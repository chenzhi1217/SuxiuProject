package com.suxiunet.repair.businiss.order.orderlist.proxy

import android.content.Context
import com.suxiunet.data.api.OrderApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.data.entity.order.OrderSignEntity
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.order.orderlist.request.OrderSignRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/25
 * desc   :
 */
class OrderSignProxy : RefreshProxy<OrderSignRequest, OrderSignEntity> {
    var mApiImpl: OrderApiImpl
    constructor(context: Context): super(context){
        mApiImpl = OrderApiImpl(context)
    }
    override fun getObservable(request: OrderSignRequest): Observable<OrderSignEntity> {
        return mApiImpl.getAliPayOrderInfo(request.orderNo,request.total_amount,request.body,request.subject)
                .map {
                    it.retData
                }
    }
}