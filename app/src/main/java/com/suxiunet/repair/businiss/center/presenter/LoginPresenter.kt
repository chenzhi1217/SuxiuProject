package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.LoginContract

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class LoginPresenter(activity:Activity,view:LoginContract.View,model:LoginContract.Model): BasicPresenter<LoginContract.View, LoginContract.Model>(activity,view,model) {
}