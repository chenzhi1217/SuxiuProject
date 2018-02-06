package com.suxiunet.repair.businiss.order.orderlist.view

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import com.alipay.sdk.app.PayTask
import com.suxiunet.data.entity.order.OrderInfoEntity
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderDetailContract
import com.suxiunet.repair.businiss.order.orderlist.model.OrderDetailModel
import com.suxiunet.repair.businiss.order.orderlist.presenter.OrderDetailPresenter
import com.suxiunet.repair.databinding.FragOrderDetailBinding
import android.widget.Toast
import android.text.TextUtils
import com.suxiunet.data.entity.order.OrderSignEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.ali.PayResult
import com.suxiunet.repair.util.ToastUtil


/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
class OrderDetailFragment: NomalFragment<OrderDetailPresenter,FragOrderDetailBinding>(),OrderDetailContract.View{

    private var mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                SDK_PAY_FLAG -> {
                    val payResult = PayResult(msg?.obj as Map<String, String>)
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    val resultInfo = payResult.getResult()// 同步返回需要验证的信息
                    val resultStatus = payResult.getResultStatus()
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(activity, getString(R.string.pay_sucess), Toast.LENGTH_SHORT).show()
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(activity, getString(R.string.pay_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    
    companion object {
        val SDK_PAY_FLAG = 100
        //支付宝支付常量
        val PAY_BY_ALI = 0X0000_0010
        //微信支付常量
        val PAY_BY_WEIXIN = 0X0000_0011
        //记录当前的支付类型
        var mCurPayType = PAY_BY_ALI
    }
    //当前的订单信息
    var mOrderInfo: OrderInfoEntity? = null
    
    override fun initView() {
        //拿到订单详情数据
        mOrderInfo = (activity as OrderDetailActivity).getOrderInfo()
        mBinding.presenter = mPresenter
        mBinding.data = mOrderInfo
        initData(mOrderInfo)
        initLayout(mOrderInfo?.status)
    }

    /**
     * 用户选择了支付宝支付
     */
    override fun payByAli() {
        mCurPayType = PAY_BY_ALI
        if (mOrderInfo != null) {
            val orderNo = mOrderInfo!!.orderNo
            mPresenter?.getOrderSign(orderNo,"0.01","维修费用","维修费用")
        }
    }

    /**
     * 用户选择了微信支付
     */
    override fun payByWeixin() {
        mCurPayType = PAY_BY_WEIXIN
        ToastUtil.showToast("微信支付")
    }

    /**
     * 返回当前页面的Presenter
     */
    override fun getPresenter(): OrderDetailPresenter? {
        return OrderDetailPresenter(activity,this,OrderDetailModel())
    }

    /**
     * 返回当前布局
     */
    override fun getContentLayoutId(): Int {
        return R.layout.frag_order_detail
    }

    /**
     * 调起支付宝支付
     */
    fun payByAli(orderInfo: String) {
        val runnable = Runnable {
            val alipay = PayTask(activity)
            var result = alipay.payV2(orderInfo, true)
            var msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }
        val payThread = Thread(runnable)
        payThread.start()
    }

    /**
     * 获取验签成功
     */
    override fun getSignSuccess(data: OrderSignEntity?) {
        if (data != null) {
            when (mCurPayType) {
                //调起支付宝支付
                PAY_BY_ALI -> payByAli(data.body)
                //调起微信支付
                PAY_BY_WEIXIN -> ""
            }
        }
    }

    /**
     * 获取验签失败
     */
    override fun getSignError(e: ApiException?) {
        ToastUtil.showToast(R.string.signature_error)
    }

    private fun initData(orderInfo: OrderInfoEntity?) {
        var equitLx = ""
        if (orderInfo != null) {
            when (orderInfo.machineMode) {
            //A:台式机 B：笔记本 C:服务器  D：其它
                "A" -> equitLx = getString(R.string.desktop)
                "B" -> equitLx = getString(R.string.notebook)
                "C" -> equitLx = getString(R.string.server)
                "D" -> equitLx = getString(R.string.other)
            }
            mBinding.includeOrderDetailInfo.tvEquitSblx.text = equitLx
        }
    }

    /**
     * 根据当前订单的状态来设置条目的展示与隐藏
     */
    private fun initLayout(status: String?) {
        when (status) {
        //待接单，已取消  设备类型、设备型号、故障描述
            "A","E" -> {
                mBinding.includeOrderDetailInfo.rlOrderDetailMasterCode.visibility = View.GONE
                mBinding.includeOrderDetailInfo.rlOrderDetailPrice.visibility = View.GONE
                mBinding.includeOrderDetailInfo.rlOrderDetailPay.visibility = View.GONE
            }
        //服务中  设备类型、设备型号、故障描述、工程师编号
            "B" -> {
                mBinding.includeOrderDetailInfo.rlOrderDetailPrice.visibility = View.GONE
                mBinding.includeOrderDetailInfo.rlOrderDetailPay.visibility = View.GONE
                mBinding.includeOrderDetailContactUs.rlContantMaster.visibility = View.VISIBLE
            }
        //待付款，已完成  设备类型、设备型号、故障描述、工程师编号、维修金额
            "C" -> {

            }
            "D" ->{
                mBinding.includeOrderDetailInfo.rlOrderDetailPay.visibility = View.GONE
            }
        }
    }
}
