package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderDetailContract
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
class OrderDetailPresenter(activity: Activity, view: OrderDetailContract.View, model: OrderDetailContract.Model) : BasicPresenter<OrderDetailContract.View, OrderDetailContract.Model>(activity, view, model) {
    
    /**
     * 联系客服
     */
    fun contactCustomerService() {
        try {
            val url = "mqqwpa://im/chat?chat_type=wpa&uin="+ Constant.companyQQ+""
            mActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            ToastUtil.showToast("您还没有安装手机QQ")
        }
    }

    /**
     * 联系工程师
     */
    fun contactMaster() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Constant.companyPhone+""))
        mActivity.startActivity(intent)
    }
}