package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderDetailContract

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
class OrderDetailPresenter(activity: Activity,view: OrderDetailContract.View,model: OrderDetailContract.Model): BasicPresenter<OrderDetailContract.View, OrderDetailContract.Model>(activity,view,model) {
}