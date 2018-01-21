package com.suxiunet.repair.businiss.order.orderlist.view

import android.view.View
import com.suxiunet.data.entity.order.OrderInfoEntity
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderDetailContract
import com.suxiunet.repair.businiss.order.orderlist.model.OrderDetailModel
import com.suxiunet.repair.businiss.order.orderlist.presenter.OrderDetailPresenter
import com.suxiunet.repair.databinding.FragOrderDetailBinding

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
class OrderDetailFragment: NomalFragment<OrderDetailPresenter,FragOrderDetailBinding>(),OrderDetailContract.View{
    var mOrderInfo: OrderInfoEntity? = null
    override fun initView() {
        //拿到订单详情数据
        mOrderInfo = (activity as OrderDetailActivity).getOrderInfo()
        mBinding.presenter = mPresenter
        mBinding.data = mOrderInfo
        initData(mOrderInfo)
        initLayout(mOrderInfo?.status)
    }

    private fun initData(orderInfo: OrderInfoEntity?) {
        var equitLx = ""
        if (orderInfo != null) {
            when (orderInfo.machineMode) {
            //A:台式机 B：笔记本 C:服务器  D：其它
                "A" -> equitLx = "台式机"
                "B" -> equitLx = "笔记本"
                "C" -> equitLx = "服务器"
                "D" -> equitLx = "其它"
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
            "C","D" -> {
                
            }
        }
    }

    override fun getPresenter(): OrderDetailPresenter? {
        return OrderDetailPresenter(activity,this,OrderDetailModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_order_detail
    }
}
