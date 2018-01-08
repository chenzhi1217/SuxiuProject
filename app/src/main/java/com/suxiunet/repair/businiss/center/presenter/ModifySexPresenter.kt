package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.ModifySexContract

/**
 * author : chenzhi
 * time   : 2018/01/07
 * desc   : 修改性别
 */
class ModifySexPresenter(activity: Activity, view: ModifySexContract.View, model: ModifySexContract.Model): BasicPresenter<ModifySexContract.View, ModifySexContract.Model>(activity,view,model) {
}