package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.ModifyNickNameContract

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class ModifyNickNamePresenter(activity:Activity,view: ModifyNickNameContract.View,model:ModifyNickNameContract.Model): BasicPresenter<ModifyNickNameContract.View, ModifyNickNameContract.Model>(activity,view,model) {
}