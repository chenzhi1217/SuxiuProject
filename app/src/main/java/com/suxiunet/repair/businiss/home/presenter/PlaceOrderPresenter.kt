package com.suxiunet.repair.businiss.home.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单
 */
class PlaceOrderPresenter(activity: Activity,view: PlaceOrderContract.View,model: PlaceOrderContract.Model): BasicPresenter<PlaceOrderContract.View,PlaceOrderContract.Model>(activity,view,model) {
}