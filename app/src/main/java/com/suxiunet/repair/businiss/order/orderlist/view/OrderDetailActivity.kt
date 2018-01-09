package com.suxiunet.repair.businiss.order.orderlist.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/09
 * desc   : 订单详情页
 */
class OrderDetailActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("订单详情")
        startFragment(R.id.fl_basic_act,OrderDetailFragment::class.java,OrderDetailFragment::class.java.simpleName)
    }
}