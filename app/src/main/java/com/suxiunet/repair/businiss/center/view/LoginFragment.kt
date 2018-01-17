package com.suxiunet.repair.businiss.center.view

import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.SuXiuKey
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.LoginContract
import com.suxiunet.repair.businiss.center.model.LoginModel
import com.suxiunet.repair.businiss.center.presenter.LoginPresenter
import com.suxiunet.repair.databinding.FragLoginBinding
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 登录页面
 */
class LoginFragment: NomalFragment<LoginPresenter, FragLoginBinding>(),LoginContract.View {
    
    override fun initView() {
        mBinding.btLogin.setOnClickListener { 
            //拿到电话号码和短信验证码
            var phone = mBinding.etLoginPhone.text.toString().trim()
            var checkCode = mBinding.etLoginCheckcode.text.toString().trim()
            mPresenter?.login(phone,checkCode)
        }
    }

    override fun getPresenter(): LoginPresenter? {
        return LoginPresenter(activity,this,LoginModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_login
    }

    /**
     * 登录成功
     */
    override fun loginSuccess(data: UserInfoEntity?) {
        //将用户信息保存到本地
        CacheUtil.getInstance().saveCacheData(data,CacheUtil.USER_INFO)
        //将token保存到本地
        SpUtil.putString(context,SpUtil.TOKEN_KEY,data?.token)
        SpUtil.putString(context,SpUtil.LOGIN_ID_KEY,data?.loginId)
        //销毁当前页面
        activity.finish()
    }
    //登录失败
    override fun loginError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"登录失败")
    }
}
