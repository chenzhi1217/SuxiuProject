package com.suxiunet.repair.businiss.home.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
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
class PlaceOrderFragment: NomalFragment<PlaceOrderPresenter,FragPlaceOrderBinding>(),PlaceOrderContract.View, View.OnClickListener {
    override fun placeOrderSuccess() {
        ToastUtil.showToast("下单成功")
        activity.finish()
    }

    override fun placeOrderError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"下单失败")
    }

    lateinit var mAnima: Animation
    /**
     * 定位成功的回调方法
     */
    override fun locationSuccess(addr: String, street: String?) {
        stopAnima()
        mBinding.includeAddrInfo.etPlaceOrderAddr.setText(addr?:"")
        mBinding.includeAddrInfo.etPlaceOrderStreet.setText(street?:"")
    }

    /**
     * 定位失败的回调方法
     */
    override fun locationError(errInfo: String?) {
        stopAnima()
        ToastUtil.showToast(errInfo?:"定位失败")
    }

    override fun initView() {
        //初始化动画
        mAnima = AnimationUtils.loadAnimation(context,R.anim.rotate_anim)
        //调起定位功能
        startAnima()
        mPresenter?.startLocation()
        //点击下单按钮
        mBinding.btPlaceOrder.setOnClickListener(this)
        //重新定位
        mBinding.includeAddrInfo.llLocationAgain.setOnClickListener(this)
        //初始化号码
        val phone = SpUtil.getString(context, SpUtil.LOGIN_ID_KEY, "")
        mBinding.includeBasicInfo.etPlaceOrderPhone.setText(phone)
    }

    override fun getPresenter(): PlaceOrderPresenter? {
        return PlaceOrderPresenter(activity,this,PlaceOrderModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_place_order
    }

    /**
     * 开启旋转动画
     */
    fun startAnima() {
        if (mAnima != null) {
            mBinding.includeAddrInfo.ivRotate.startAnimation(mAnima)
        }
    }

    /**
     * 停止旋转动画
     */
    fun stopAnima() {
        mBinding.includeAddrInfo.ivRotate.clearAnimation()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //提交订单
            R.id.bt_place_order ->{
                //拿到订单信息
                val name = mBinding.includeBasicInfo.etPlaceOrderName.text.toString().trim()
                val phone = mBinding.includeBasicInfo.etPlaceOrderPhone.text.toString().trim()
                val time = mBinding.includeBasicInfo.etPlaceOrderTime.text.toString().trim()
                //拿到设备信息
                val equipModel = mBinding.includeEquipmentInfo.etPlaceOrderEquipmentModel.text.toString().trim()
                //拿到地址信息
                val addrs = mBinding.includeAddrInfo.etPlaceOrderAddr.text.toString().trim()
                val street = mBinding.includeAddrInfo.etPlaceOrderStreet.text.toString().trim()
                //故障描述
                val describe = mBinding.etPlaceOrderDescribe.text.toString().trim()

                //提交订单信息
                mPresenter?.placeOrder(name, phone, time, "", equipModel, addrs, street, describe)
            }
            R.id.ll_location_again ->{
                startAnima()
                mPresenter?.startLocation()
            }
        }
    }
}