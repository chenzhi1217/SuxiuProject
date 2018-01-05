package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 登录界面
 */
class LoginActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("登录")
        startFragment(R.id.fl_basic_act,LoginFragment::class.java,LoginFragment::class.java.simpleName)
    }
}