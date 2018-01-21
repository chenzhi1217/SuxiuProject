package com.suxiunet.repair.businiss.order.orderlist.holder

import android.view.ViewGroup
import com.suxiunet.data.entity.order.OrderInfoEntity
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.businiss.order.orderlist.presenter.OrderListPresenter
import com.suxiunet.repair.databinding.ItemOrderListBinding

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单列表条目
 */
class OrderListHolder(parent: ViewGroup, resId: Int) : BasicHolder<OrderInfoEntity, ItemOrderListBinding>(parent, resId) {
    
    var mPresenter: OrderListPresenter? = null
    
    constructor(parent: ViewGroup, resId: Int, presenter: OrderListPresenter?):this(parent,resId){
        this.mPresenter = presenter
        mBinding.presenter = mPresenter
    }
    override fun bindData(data: OrderInfoEntity?) {
        //A:台式机 B：笔记本 C:服务器  D：其它
        mBinding.data = data
        if (data != null) {
            var machineMode = ""
            when (mBinding.data.machineMode) {
                "A" -> machineMode = "台式机"
                "B" -> machineMode = "笔记本"
                "C" -> machineMode = "服务器"
                "D" -> machineMode = "其它"
            }
            mBinding.tvItemOrderEquitModel.text = machineMode
        }
    }
}