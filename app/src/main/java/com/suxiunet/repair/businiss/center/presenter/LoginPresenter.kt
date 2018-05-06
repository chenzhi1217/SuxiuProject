package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.text.TextUtils
import android.widget.Button
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.LoginContract
import com.suxiunet.repair.businiss.center.proxy.GetVeriCodeProxy
import com.suxiunet.repair.businiss.center.proxy.UserLoginProxy
import com.suxiunet.repair.businiss.center.request.LoginRequest
import com.suxiunet.repair.util.ToastUtil
import com.suxiunet.repair.view.CountDownTimerUtils

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class LoginPresenter : BasicPresenter<LoginContract.View, LoginContract.Model> {
    
    var mLoginProxy: UserLoginProxy
    var mGetCodeProxy: GetVeriCodeProxy
    var mRequest = LoginRequest()

    constructor(activity: Activity, view: LoginContract.View, model: LoginContract.Model) : super(activity, view, model){
        mLoginProxy = UserLoginProxy(mActivity)
        mGetCodeProxy = GetVeriCodeProxy(mActivity)
    }
    
    /**
     * 登录接口
     */
    fun login(phone: String, checkCode: String) {
        //对输入的数据进行校验
        if (!checkValue(phone,checkCode)) {
            return
        }
        loading?.show()
        //设置网络请求结果的接口回调
        mLoginProxy.setCallBack(object : BasicProxy.ProxyCallBack<LoginRequest, UserInfoEntity>{
            //登录成功
            override fun onLoadSuccess(req: LoginRequest?, type: BasicProxy.ProxyType, data: UserInfoEntity?) {
                loading?.dismiss()
                mView.loginSuccess(data)
            }
            //登录失败
            override fun onLoadError(req: LoginRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                loading?.dismiss()
                mView.loginError(e)
            }
        })
        mRequest.setLoginName(phone)
        mRequest.setCheckCode(checkCode)
        mLoginProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
    }

    /**
     * 获取短信验证码
     */
    fun getSendCode(phone: String, btGetCode: Button) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号码")
            return 
        }
        setButtonState(btGetCode)
        mGetCodeProxy.setCallBack(object: BasicProxy.ProxyCallBack<LoginRequest,Any>{
            //短信验证码获取成功
            override fun onLoadSuccess(req: LoginRequest?, type: BasicProxy.ProxyType, data: Any?) {
                mView.getVeriCodeSucess()
            }
            //短信验证码获取失败
            override fun onLoadError(req: LoginRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.getVeriCodeError(e)
            }
        })
        mRequest.setLoginName(phone)
        mGetCodeProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
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

    private var countDownTimerUtils: CountDownTimerUtils? = null

    /**
     * 让进入界面就开始倒计时
     */
    private fun setButtonState(btGetCode: Button) {
        if (countDownTimerUtils == null) {
            countDownTimerUtils = CountDownTimerUtils(btGetCode, 60000, 1000)
        }
        countDownTimerUtils?.start()
    }
}