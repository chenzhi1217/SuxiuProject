package com.suxiunet.repair.businiss.home.view

import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract
import com.suxiunet.repair.businiss.home.model.PlaceOrderModel
import com.suxiunet.repair.businiss.home.presenter.PlaceOrderPresenter
import com.suxiunet.repair.databinding.FragPlaceOrderBinding
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单页面
 */
class PlaceOrderFragment: NomalFragment<PlaceOrderPresenter,FragPlaceOrderBinding>(),PlaceOrderContract.View {
    /**
     * 定位成功的回调方法
     */
    override fun locationSuccess(addr: String, street: String?) {
        mBinding.etPlaceOrderAddr.setText(addr?:"")
        mBinding.etPlaceOrderStreet.setText(street?:"")
    }

    /**
     * 定位失败的回调方法
     */
    override fun locationError(errInfo: String?) {
        ToastUtil.showToast(errInfo?:"定位失败")
    }

    override fun initView() {
        //调起定位功能
        mPresenter?.startLocation()
    }

    override fun getPresenter(): PlaceOrderPresenter? {
        return PlaceOrderPresenter(activity,this,PlaceOrderModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_place_order
    }
}