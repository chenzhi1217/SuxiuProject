package com.suxiunet.repair.businiss.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.suxiunet.data.entity.user.HomeEntity
import com.suxiunet.repair.R
import com.suxiunet.repair.anim.YAnimation
import com.suxiunet.repair.base.Constant
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
class HomeFragment : BasicFragment<HomeRequest, HomePresenter, HomeEntity, FragHomeBinding>(),HomeContract.View {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showHomeTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置动画
        var animation = YAnimation()
        animation.repeatCount = Animation.INFINITE
        mBinding.includeOrderCount.ivFragHomeLogo.startAnimation(animation)
        mBinding.presenter = mPresenter
    }
    override fun onCreateProxy(): RefreshProxy<HomeRequest, HomeEntity> {
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

    override fun onLoadDataSuccess(req: HomeRequest?, data: HomeEntity?) {
        super.onLoadDataSuccess(req, data)
        //设置轮播图
        if (data?.bannerDtos != null) {
            setLunboData(data?.bannerDtos)
        }
    }

    /**
     * 初始化轮播图数据
     */
    fun setLunboData(bannerDtos: MutableList<HomeEntity.BannerDtosBean>) {
        var images: ArrayList<String> = ArrayList()
        for (bannerDto in bannerDtos) {
            images.add(Constant.baseImage + bannerDto.img)
        }
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()
    }

    override fun showErrorView() {
        showContentView()
    }
}