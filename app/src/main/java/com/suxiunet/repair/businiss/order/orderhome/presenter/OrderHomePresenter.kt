package com.suxiunet.repair.businiss.order.orderhome.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.order.orderhome.contract.OrderHomeContract

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单首页
 */
class OrderHomePresenter(activity: Activity,view: OrderHomeContract.View,model:OrderHomeContract.Model): BasicPresenter<OrderHomeContract.View, OrderHomeContract.Model>(activity,view,model) {
}