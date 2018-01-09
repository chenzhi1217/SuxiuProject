package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import android.content.Intent
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderListContract
import com.suxiunet.repair.businiss.order.orderlist.view.OrderDetailActivity

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单列表
 */
class OrderListPresenter(activity: Activity, view: OrderListContract.View, model: OrderListContract.Model) : BasicPresenter<OrderListContract.View, OrderListContract.Model>(activity, view, model) {

    /**
     * 进入订单详情
     */
    fun goOrderDetail() {
        mActivity.startActivity(Intent(mActivity,OrderDetailActivity::class.java))
    }
}