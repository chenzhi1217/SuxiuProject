package com.suxiunet.repair.businiss.order.orderlist.view

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
    override fun initView() {
        mBinding.presenter = mPresenter
    }

        override fun getPresenter(): OrderDetailPresenter? {
        return OrderDetailPresenter(activity,this,OrderDetailModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_order_detail
    }
}
