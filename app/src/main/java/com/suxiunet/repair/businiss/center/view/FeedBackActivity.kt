package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicActivity

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
class FeedBackActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("建议与反馈")
        startFragment(R.id.fl_basic_act,FeedBackFragment::class.java,FeedBackFragment::class.java.simpleName)
    }
}