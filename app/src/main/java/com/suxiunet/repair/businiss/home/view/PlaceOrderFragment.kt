package com.suxiunet.repair.businiss.home.view

import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract
import com.suxiunet.repair.businiss.home.model.PlaceOrderModel
import com.suxiunet.repair.businiss.home.presenter.PlaceOrderPresenter
import com.suxiunet.repair.databinding.FragPlaceOrderBinding

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单页面
 */
class PlaceOrderFragment: NomalFragment<PlaceOrderPresenter,FragPlaceOrderBinding>(),PlaceOrderContract.View {
    override fun initView() {
    }

    override fun getPresenter(): PlaceOrderPresenter? {
        return PlaceOrderPresenter(activity,this,PlaceOrderModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_place_order
    }
}