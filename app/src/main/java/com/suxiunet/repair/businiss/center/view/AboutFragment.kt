package com.suxiunet.repair.businiss.center.view

import android.widget.TextView
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.AboutContract
import com.suxiunet.repair.businiss.center.model.AboutModel
import com.suxiunet.repair.businiss.center.presenter.AboutPresenter
import com.suxiunet.repair.databinding.FragAboutBinding

/**
 * author : chenzhi
 * time   : 2018/01/08 
 * desc   : 关于我们
 */
class AboutFragment: NomalFragment<AboutPresenter, FragAboutBinding>(),AboutContract.View {
    override fun initView() = Unit
    override fun getPresenter(): AboutPresenter? {
        return AboutPresenter(activity,this,AboutModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_about
    }
}
