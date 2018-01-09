package com.suxiunet.repair.businiss.order.orderlist.view

import android.databinding.ViewDataBinding
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.base.adapter.BaseRecyclerViewAdapter
import com.suxiunet.repair.base.baseui.BasicRecyclerViewFragment
import com.suxiunet.repair.businiss.home.proxy.HomeProxy
import com.suxiunet.repair.businiss.order.orderlist.contract.OrderListContract
import com.suxiunet.repair.businiss.order.orderlist.holder.OrderListHolder
import com.suxiunet.repair.businiss.order.orderlist.model.OrderListModel
import com.suxiunet.repair.businiss.order.orderlist.presenter.OrderListPresenter
import com.suxiunet.repair.businiss.request.HomeRequest

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 订单列表
 */
class OrderListFragment: BasicRecyclerViewFragment<HomeRequest, OrderListPresenter, Any, Any>(),OrderListContract.View {
    override fun initView() {
    }

    companion object {
        val TYPE_ITEM_ORDER_LIST = R.layout.item_order_list
    }
    
    override fun onCreateViewTypeMapper(): BaseRecyclerViewAdapter.ViewTypeMapper<Any>? {
        return object : BaseRecyclerViewAdapter.ViewTypeMapper<Any>{
            override fun onMapViewType(d: Any?, position: Int): Int {
                return TYPE_ITEM_ORDER_LIST
            }
        }
    }

    override fun onCreateProxy(): RefreshProxy<HomeRequest, Any> {
        return HomeProxy(context)
    }

    override fun getRequestData(): HomeRequest {
        return HomeRequest()
    }

    override fun getPresenter(): OrderListPresenter? {
        return OrderListPresenter(activity,this,OrderListModel())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasicHolder<out Any, out ViewDataBinding> {
        return OrderListHolder(parent!!,viewType,mPresenter)
    }

    override fun converDataToList(data: Any?): List<Any>? {
        var list = ArrayList<String>()
        for (i in 0 until 15) {
            list.add("")
        }
        return list
    }
}