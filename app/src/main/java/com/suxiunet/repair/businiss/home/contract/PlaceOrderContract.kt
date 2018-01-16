package com.suxiunet.repair.businiss.home.contract

import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单
 */
interface PlaceOrderContract {
    interface View: IView{
        fun locationSuccess(addr: String ,street: String?)
        fun locationError(errInfo: String?)
    }
    interface Model: IModel
}