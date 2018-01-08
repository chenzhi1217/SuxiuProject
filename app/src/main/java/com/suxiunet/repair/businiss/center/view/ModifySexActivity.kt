package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/07
 * desc   : 修改性别
 */
class ModifySexActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("修改性别")
        startFragment(R.id.fl_basic_act,ModifySexFragment::class.java,ModifySexFragment::class.java.simpleName)
    }
}