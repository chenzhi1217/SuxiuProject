package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.AboutContract

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 关于我们
 */
class AboutPresenter(activity: Activity,view: AboutContract.View,model: AboutContract.Model): BasicPresenter<AboutContract.View, AboutContract.Model>(activity,view,model) {
}