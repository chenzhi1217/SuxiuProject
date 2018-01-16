package com.suxiunet.repair.businiss.home.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract
import com.suxiunet.repair.plugin.AliMapCode

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单
 */
class PlaceOrderPresenter(activity: Activity, view: PlaceOrderContract.View, model: PlaceOrderContract.Model) : BasicPresenter<PlaceOrderContract.View, PlaceOrderContract.Model>(activity, view, model) {

    fun startLocation() {
        AliMapCode.setOnceLocation()
        AliMapCode.startLocation(mActivity,object : AliMapCode.LocationChangeListener{
            override fun locationDataSuccess(latitude: String?, longitude: String?, province: String?, city: String?, district: String?, street: String?) {
                mView.locationSuccess(province + " " + city + " " + district,street)
            }

            override fun locationDataFail(errorCode: Int, errorInfo: String?) {
                mView.locationError(errorInfo)
            }
        })
    }
}