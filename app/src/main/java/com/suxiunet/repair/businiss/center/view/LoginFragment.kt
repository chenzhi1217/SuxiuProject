package com.suxiunet.repair.businiss.center.view

import com.suxiunet.repair.R
import com.suxiunet.repair.base.NomalFragment
import com.suxiunet.repair.businiss.center.contract.LoginContract
import com.suxiunet.repair.businiss.center.model.LoginModel
import com.suxiunet.repair.businiss.center.presenter.LoginPresenter
import com.suxiunet.repair.databinding.FragLoginBinding

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   :
 */
class LoginFragment: NomalFragment<LoginPresenter, FragLoginBinding>(),LoginContract.View {
    override fun initView() {
    }

    override fun getPresenter(): LoginPresenter? {
        return LoginPresenter(activity,this,LoginModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_login
    }
}
