package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import android.content.Intent
import com.suxiunet.data.entity.order.OrderInfoEntity
import com.suxiunet.data.entity.order.OrderListEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderListContract
import com.suxiunet.repair.businiss.order.orderlist.proxy.OrderListProxy
import com.suxiunet.repair.businiss.order.orderlist.request.OrderListRequest
import com.suxiunet.repair.businiss.order.orderlist.view.OrderDetailActivity

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单列表
 */
class OrderListPresenter : BasicPresenter<OrderListContract.View, OrderListContract.Model> {
    var mProxy: OrderListProxy
    var mRequest: OrderListRequest
    constructor(activity: Activity, view: OrderListContract.View, model: OrderListContract.Model): super(activity, view, model){
        mProxy = OrderListProxy(mActivity)
        mRequest = OrderListRequest()
    }
    
    /**
     * 进入订单详情
     */
    fun goOrderDetail(data: OrderInfoEntity) {
        val intent = Intent(mActivity, OrderDetailActivity::class.java)
        intent.putExtra(OrderDetailActivity.ORDER_INFO_KEY,data)
        mActivity.startActivity(intent)
    }
}