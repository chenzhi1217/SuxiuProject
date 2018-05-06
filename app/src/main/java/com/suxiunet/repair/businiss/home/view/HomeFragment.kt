package com.suxiunet.repair.businiss.home.view

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Animation
import com.suxiunet.data.entity.user.HomeEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.anim.YAnimation
import com.suxiunet.repair.base.BasicProxy
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

    override fun initLoadData() {
        if (!mDataProxy.isLoading()) {
            mDataProxy.request(getRequestData(), BasicProxy.ProxyType.LOAD_DATA)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M) 
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置动画
        var animation = YAnimation()
        animation.repeatCount = Animation.INFINITE
        mBinding.includeOrderCount.ivFragHomeLogo.startAnimation(animation)
        mBinding.presenter = mPresenter
        
        setLunboData()
  
        /*mBinding.svHomeFrag.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> 
            if (scrollY == 0) {
                mParentBinding.sfBasicFrag.setEnabled(true)
            } else {
                mParentBinding.sfBasicFrag.setEnabled(false)
            }
        }*/
    }

    override fun onLoadDataError(req: HomeRequest?, e: ApiException?) {
        super.onLoadDataError(req, e)
        mParentBinding.sfBasicFrag.isRefreshing = false
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
        mParentBinding.sfBasicFrag.isRefreshing = false
        //设置轮播图
        if (data?.bannerDtos != null) {
//            setLunboData()
            CacheUtil.getInstance().saveCacheData(data,"homeData")
        }
        //设置活动
        mBinding?.includeActivity?.itemHomePostTv?.text = data?.notice
        
    }

    /**
     * 初始化轮播图数据
     */
    fun setLunboData() {
        var images: ArrayList<String> = ArrayList()
        /*for (bannerDto in bannerDtos) {
            images.add(Constant.baseImage + bannerDto.img)
        }*/
        for (i in 0..3) {
            images.add("")
        }
        
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()
    }

    override fun showErrorView() {
        showContentView()
        //设置轮播图
        val homeEntity = CacheUtil.getInstance().getCacheData("homeData", HomeEntity::class.java)
        if (homeEntity != null && homeEntity.bannerDtos != null) {
//            setLunboData(homeEntity.bannerDtos)
            //设置活动
            mBinding?.includeActivity?.itemHomePostTv?.text = homeEntity?.notice
        }
    }
}