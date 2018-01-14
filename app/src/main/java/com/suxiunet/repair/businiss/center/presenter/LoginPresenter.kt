package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.text.TextUtils
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.LoginContract
import com.suxiunet.repair.businiss.center.proxy.UserLoginProxy
import com.suxiunet.repair.businiss.center.request.LoginRequest
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class LoginPresenter(activity: Activity, view: LoginContract.View, model: LoginContract.Model) : BasicPresenter<LoginContract.View, LoginContract.Model>(activity, view, model) {
    
    var mProxy = UserLoginProxy(mActivity)
    var mRequest = LoginRequest()

    fun login(phone: String, checkCode: String) {
        //对输入的数据进行校验
        if (!checkValue(phone,checkCode)) {
            return
        }
        //设置网络请求结果的接口回调
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<LoginRequest, UserInfoEntity>{
            //登录成功
            override fun onLoadSuccess(req: LoginRequest?, type: BasicProxy.ProxyType, data: UserInfoEntity?) {
                mView.loginSuccess(data)
            }
            //登录失败
            override fun onLoadError(req: LoginRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.loginError(e)
            }

        })
        mRequest.setLoginName(phone)
        mRequest.setCheckCode(checkCode)
        mProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
    }

    /**
     * 对手机号跟短信验证码进行校验
     */
    private fun checkValue(phone: String, checkCode: String): Boolean {
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号码")
            return false
        }else if (TextUtils.isEmpty(checkCode)) {
            ToastUtil.showToast("请输入短信验证码")
            return false
        }
        return true
    }
}