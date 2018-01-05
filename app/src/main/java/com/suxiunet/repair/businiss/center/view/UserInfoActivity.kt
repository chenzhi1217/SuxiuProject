package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 
 */
class UserInfoActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("用户信息")
        startFragment(R.id.fl_basic_act,UserInfoFragment::class.java,UserInfoFragment::class.java.simpleName)
    }
}