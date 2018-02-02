package com.suxiunet.repair.businiss.order.orderlist.proxy

import android.content.Context
import com.suxiunet.data.api.OrderApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.data.entity.order.OrderInfoEntity
import com.suxiunet.data.entity.order.OrderListEntity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.order.orderlist.request.OrderListRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   : 订单列表查询
 */
class OrderListProxy: RefreshProxy<OrderListRequest, OrderListEntity> {

    var mOrderApiImpl: OrderApiImpl
    
    constructor(context: Context): super(context){
        mOrderApiImpl = OrderApiImpl(context)
    }

    override fun getObservable(request: OrderListRequest): Observable<OrderListEntity> {
        return mOrderApiImpl.getOrderList(request.loginId,request.status,"C")
                .map(object : Func1<ApiResponse<OrderListEntity>,OrderListEntity>{
                    override fun call(t: ApiResponse<OrderListEntity>?): OrderListEntity {
                        return t?.retData?:OrderListEntity()
                    }
                })
    }
    
}