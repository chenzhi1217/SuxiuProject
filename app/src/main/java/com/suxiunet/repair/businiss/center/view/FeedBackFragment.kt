package com.suxiunet.repair.businiss.center.view

import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.FeedBackContract
import com.suxiunet.repair.businiss.center.model.FeedBackModel
import com.suxiunet.repair.businiss.center.presenter.FeedBackPresenter
import com.suxiunet.repair.databinding.FragFeedBackBinding
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
class FeedBackFragment: NomalFragment<FeedBackPresenter, FragFeedBackBinding>(),FeedBackContract.View {
    override fun submitSuccess() {
        ToastUtil.showToast("提交成功")
        activity.finish()
    }

    override fun submitError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"提交失败")
    }

    override fun initView() {
        mBinding.btFeedBack.setOnClickListener { 
            //拿到反馈内容
            val feedBackContent = mBinding.etFeedBackContent.text.toString().trim()
            mPresenter?.subFeedBack(feedBackContent)
        }
    }

    override fun getPresenter(): FeedBackPresenter? {
        return FeedBackPresenter(activity,this,FeedBackModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_feed_back
    }
}