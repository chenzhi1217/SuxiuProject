package com.suxiunet.repair.businiss.center.view

import android.app.Activity
import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 修改昵称
 */
class ModifyNickNameActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("修改昵称")
        startFragment(R.id.fl_basic_act,ModifyNickNameFragment::class.java,ModifyNickNameFragment::class.java.simpleName)
    }
}