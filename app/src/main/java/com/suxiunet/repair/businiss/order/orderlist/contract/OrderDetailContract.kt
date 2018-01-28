package com.suxiunet.repair.businiss.order.orderlist.contract

import com.suxiunet.data.entity.order.OrderSignEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情
 */
interface OrderDetailContract {
    interface View: IView{
        fun getSignSuccess(data: OrderSignEntity?)
        fun getSignError(e: ApiException?)
    }
    interface Model: IModel
}