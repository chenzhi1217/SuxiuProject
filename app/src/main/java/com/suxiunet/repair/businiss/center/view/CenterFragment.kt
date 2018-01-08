package com.suxiunet.repair.businiss.center.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.CenterContract
import com.suxiunet.repair.businiss.center.model.CenterModel
import com.suxiunet.repair.businiss.center.presenter.CenterPresenter
import com.suxiunet.repair.databinding.FragCenterBinding

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   : 个人中心页面
 */
class CenterFragment : NomalFragment<CenterPresenter, FragCenterBinding>(),CenterContract.View {
    override fun initView() {
        mBinding.presenter = mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showCenterTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun getPresenter(): CenterPresenter? {
        return CenterPresenter(activity,this,CenterModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_center
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (activity as MainActivity).showCenterTitle()
        }
    }
}