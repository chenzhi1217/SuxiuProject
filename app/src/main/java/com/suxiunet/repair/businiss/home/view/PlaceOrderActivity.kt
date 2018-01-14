package com.suxiunet.repair.businiss.home.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单
 */
class PlaceOrderActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("一键下单")
        startFragment(R.id.fl_basic_act,PlaceOrderFragment::class.java,PlaceOrderFragment::class.java.simpleName)
    }
}