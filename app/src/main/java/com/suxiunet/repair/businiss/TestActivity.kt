package com.suxiunet.repair.businiss

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   :
 */
class TestActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("测试页面")
        startFragment(R.id.fl_basic_act,TestFragment::class.java,"TestFragment")
    }
}