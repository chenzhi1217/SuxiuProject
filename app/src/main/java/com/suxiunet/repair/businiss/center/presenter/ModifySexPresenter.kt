package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.ModifySexContract
import com.suxiunet.repair.businiss.center.proxy.ModifyGenderProxy
import com.suxiunet.repair.businiss.center.request.UserInfoRequest

/**
 * author : chenzhi
 * time   : 2018/01/07
 * desc   : 修改性别
 */
class ModifySexPresenter : BasicPresenter<ModifySexContract.View, ModifySexContract.Model>{

    var mProxy: ModifyGenderProxy
    var mRequest: UserInfoRequest
    constructor(activity: Activity, view: ModifySexContract.View, model: ModifySexContract.Model): super(activity, view, model){
        mProxy = ModifyGenderProxy(mActivity)
        mRequest = UserInfoRequest()
    }

    /**
     * 修改性别
     */
    fun modifyGendent(gendent: String) {
        loading?.show()
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<UserInfoRequest,Any>{
            override fun onLoadSuccess(req: UserInfoRequest?, type: BasicProxy.ProxyType, data: Any?) {
                loading?.dismiss()
                mView.modifyGendentSuccess(gendent)
            }

            override fun onLoadError(req: UserInfoRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                loading?.dismiss()
                mView.modifyGendentError(e)
            }
        })
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")
        mRequest.gender = gendent
        mRequest.loginId = loginId
        mProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
    }
    
}