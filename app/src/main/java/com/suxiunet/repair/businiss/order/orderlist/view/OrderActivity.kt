package com.suxiunet.repair.businiss.order.orderlist.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/02/04
 * desc   :
 */
class OrderActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra("title")
        val type = intent.getStringExtra("type")
        setToolBarTitle(title)
        startFragment(R.id.fl_basic_act,OrderListFragment(type),OrderListFragment::class.java.simpleName)
    }
}