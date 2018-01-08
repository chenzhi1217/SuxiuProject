package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderListContract

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单列表
 */
class OrderListPresenter(activity: Activity,view: OrderListContract.View,model: OrderListContract.Model): BasicPresenter<OrderListContract.View, OrderListContract.Model>(activity,view,model) {
}