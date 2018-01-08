package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 关于我们
 */
class AboutActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("关于速修")
        startFragment(R.id.fl_basic_act,AboutFragment::class.java,AboutFragment::class.java.simpleName)
    }
}