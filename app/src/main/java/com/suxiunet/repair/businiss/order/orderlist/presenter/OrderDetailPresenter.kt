package com.suxiunet.repair.businiss.order.orderlist.presenter

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.view.LayoutInflater
import com.suxiunet.data.entity.order.OrderSignEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderDetailContract
import com.suxiunet.repair.businiss.order.orderlist.proxy.OrderSignProxy
import com.suxiunet.repair.businiss.order.orderlist.request.OrderSignRequest
import com.suxiunet.repair.databinding.DialogPayBinding
import com.suxiunet.repair.util.DialogUtils
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
class OrderDetailPresenter : BasicPresenter<OrderDetailContract.View, OrderDetailContract.Model> {
    var mProxy: OrderSignProxy
    var mRequest: OrderSignRequest

    var mPayBinding: DialogPayBinding
    var mPayDialog: Dialog? = null

    constructor(activity: Activity, view: OrderDetailContract.View, model: OrderDetailContract.Model) : super(activity, view, model) {
        mProxy = OrderSignProxy(mActivity)
        mRequest = OrderSignRequest()

        mPayBinding = DataBindingUtil.inflate<DialogPayBinding>(LayoutInflater.from(mActivity), R.layout.dialog_pay, null, false)
        mPayDialog = DialogUtils.getInstance().setBottomDialog(mActivity, mPayBinding.root, true, R.style.Dialog_animal)

    }

    /**
     * 联系客服
     */
    fun contactCustomerService() {
        try {
            val url = "mqqwpa://im/chat?chat_type=wpa&uin=" + Constant.companyQQ + ""
            mActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            ToastUtil.showToast("您还没有安装手机QQ")
        }
    }

    /**
     * 联系工程师
     */
    fun contactMaster(masterPhone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + masterPhone))
        mActivity.startActivity(intent)
    }

    /**
     * 选择支付方式
     */
    fun choosePayType() {
        mPayDialog?.show()
        mPayBinding.llPayByAli.setOnClickListener {
            mView.payByAli()
            mPayDialog?.dismiss()
        }
        mPayBinding.llPayByWeixin.setOnClickListener {
            mView.payByWeixin()
            mPayDialog?.dismiss()
        }
    }

    /**
     * 从后台获取订单验签
     */
    fun getOrderSign(orderNo: String, total: String, body: String, subject: String) {
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<OrderSignRequest, OrderSignEntity> {
            override fun onLoadSuccess(req: OrderSignRequest?, type: BasicProxy.ProxyType, data: OrderSignEntity?) {
                mView.getSignSuccess(data)
            }
            override fun onLoadError(req: OrderSignRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.getSignError(e)
            }
        })
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")
        mRequest.loginId = loginId
        mRequest.orderNo = orderNo
        mRequest.total_amount = total
        mRequest.body = body
        mRequest.subject = subject

        mProxy.request(mRequest, BasicProxy.ProxyType.REFRESH_DATA)
    }
}