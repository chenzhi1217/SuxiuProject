package com.suxiunet.repair.businiss.center.view

import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.FeedBackContract
import com.suxiunet.repair.businiss.center.model.FeedBackModel
import com.suxiunet.repair.businiss.center.presenter.FeedBackPresenter
import com.suxiunet.repair.databinding.FragFeedBackBinding

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
class FeedBackFragment: NomalFragment<FeedBackPresenter, FragFeedBackBinding>(),FeedBackContract.View {
    override fun initView() {
    }

    override fun getPresenter(): FeedBackPresenter? {
        return FeedBackPresenter(activity,this,FeedBackModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_feed_back
    }
}