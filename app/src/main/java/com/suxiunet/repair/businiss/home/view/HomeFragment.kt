package com.suxiunet.repair.businiss.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.suxiunet.repair.R
import com.suxiunet.repair.anim.YAnimation
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置轮播图
        setLunboData()
        setActivityData()
        //设置动画
        var animation = YAnimation()
        animation.repeatCount = Animation.INFINITE
        mBinding.includeOrderCount.ivFragHomeLogo.startAnimation(animation)
        mBinding.presenter = mPresenter
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

    /**
     * 初始化轮播图数据
     */
    fun setLunboData() {
        var images: ArrayList<String> = ArrayList()
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517892853316&di=3dde0fe1c4fd43bb22aa8e6e7288e986&imgtype=0&src=http%3A%2F%2Fpic90.huitu.com%2Fres%2F20161119%2F732704_20161119114417229020_1.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=12c10b2ac43a5194107a105ae2235cae&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F42%2F3c4a16697485ff3717379118de096f7e.jpg")
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()
    }

    /**
     * 设置跑马灯公告
     */
    fun setActivityData() {
        mBinding.includeActivity.itemHomePostTv.isMarqueeEnable = true
    }

    override fun showErrorView() {
        showContentView()
    }
}