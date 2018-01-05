package com.suxiunet.repair.businiss.home.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.home.contract.HomeContract

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页的Presenter
 */
class HomePresenter : BasicPresenter<HomeContract.View, HomeContract.Model> {
    
    constructor(activity:Activity,view: HomeContract.View,model: HomeContract.Model): super(activity,view,model){
        
    }
    
}