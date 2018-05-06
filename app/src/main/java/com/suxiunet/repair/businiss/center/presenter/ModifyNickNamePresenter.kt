package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.text.TextUtils
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.ModifyNickNameContract
import com.suxiunet.repair.businiss.center.proxy.ModifyNickNameProxy
import com.suxiunet.repair.businiss.center.request.UserInfoRequest
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class ModifyNickNamePresenter : BasicPresenter<ModifyNickNameContract.View, ModifyNickNameContract.Model> {
    var mProxy: ModifyNickNameProxy
    var mRequest: UserInfoRequest
    constructor(activity: Activity, view: ModifyNickNameContract.View, model: ModifyNickNameContract.Model):super(activity, view, model){
        mProxy = ModifyNickNameProxy(mActivity)
        mRequest = UserInfoRequest()
    }

    /**
     * 修改昵称
     */
    fun modifyNickName(newName: String) {
        loading?.show()
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<UserInfoRequest,Any>{
            override fun onLoadSuccess(req: UserInfoRequest?, type: BasicProxy.ProxyType, data: Any?) {
                loading?.dismiss()
                mView.modifySuccess(newName)
            }

            override fun onLoadError(req: UserInfoRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                loading?.dismiss()
                mView.modifyError(e)
            }
        })
        if (TextUtils.isEmpty(newName)) {
            ToastUtil.showToast("请输入昵称")
            return
        }
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")
        mRequest.nickName = newName
        mRequest.loginId = loginId
        mProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
    }
}