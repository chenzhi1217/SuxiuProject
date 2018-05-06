package com.suxiunet.repair.base

import android.app.Activity
import com.suxiunet.repair.view.LoadingDialog

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   :
 */
open class BasicPresenter<VIEW: IView,MODEL: IModel>(): IPresenter {
    override fun detachView() {
    }
    
    protected lateinit var mActivity: Activity
    protected lateinit var mView: VIEW
    protected lateinit var mModel: MODEL
    protected val loading by lazy { LoadingDialog(mActivity!!) }
    
    constructor(activity:Activity,view: VIEW,model: MODEL):this(){
        this.mActivity = activity
        this.mView = view
        this.mModel = model
    }

    protected fun showToast(msg: String) {
    }
}