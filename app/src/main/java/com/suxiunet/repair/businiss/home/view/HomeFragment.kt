package com.suxiunet.repair.businiss.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.BasicFragment
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.home.contract.HomeContract
import com.suxiunet.repair.businiss.home.model.HomeModel
import com.suxiunet.repair.businiss.home.presenter.HomePresenter
import com.suxiunet.repair.businiss.home.proxy.HomeProxy
import com.suxiunet.repair.businiss.request.HomeRequest
import com.suxiunet.repair.databinding.FragHomeBinding

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   : 首页
 */
class HomeFragment : BasicFragment<HomeRequest, HomePresenter, Any, FragHomeBinding>(),HomeContract.View {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showHomeTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onCreateProxy(): RefreshProxy<HomeRequest, Any> {
        return HomeProxy(context)
    }

    override fun getRequestData(): HomeRequest {
        return HomeRequest()
    }

    override fun getPresenter(): HomePresenter? {
        return HomePresenter(activity,this,HomeModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_home
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (activity as MainActivity).showHomeTitle()
        }
    }
}