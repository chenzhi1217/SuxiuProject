package com.suxiunet.repair.businiss.home.view

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.base.adapter.BaseRecyclerViewAdapter
import com.suxiunet.repair.base.baseui.BasicRecyclerViewFragment
import com.suxiunet.repair.businiss.home.contract.HomeContract
import com.suxiunet.repair.businiss.home.holder.HomeHeadHolder
import com.suxiunet.repair.businiss.home.holder.HomeNewsHolder
import com.suxiunet.repair.businiss.home.model.HomeModel
import com.suxiunet.repair.businiss.home.presenter.HomePresenter
import com.suxiunet.repair.businiss.home.proxy.HomeProxy
import com.suxiunet.repair.businiss.request.HomeRequest
import kotlin.collections.ArrayList

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   : 首页
 */
class HomeFragment : BasicRecyclerViewFragment<HomeRequest, HomePresenter, Any, Any>(),HomeContract.View {
    
    companion object {
        val TYPE_HEAD = R.layout.frag_home
        val TYPE_NEWS = R.layout.item_home_news
    }
    
    override fun onCreateProxy(): RefreshProxy<HomeRequest, Any> {
        return HomeProxy(context)
    }

    override fun initView() {
    }

    override fun onCreateViewTypeMapper(): BaseRecyclerViewAdapter.ViewTypeMapper<Any>? {
        return BaseRecyclerViewAdapter.ViewTypeMapper<Any> { d, position ->
            if (position == 0) {
                TYPE_HEAD
            } else if (d is Int) {
                TYPE_NEWS
            } else {
                0
            }
        }
    }

    override fun getRequestData(): HomeRequest {
        return HomeRequest()
    }

    override fun getPresenter(): HomePresenter? {
        return HomePresenter(activity,this,HomeModel())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasicHolder<out Any, out ViewDataBinding> {
        when (viewType) {
            TYPE_HEAD -> return HomeHeadHolder(parent, viewType,mPresenter)
            TYPE_NEWS -> return HomeNewsHolder(parent,viewType,mPresenter)
            else -> return HomeHeadHolder(parent, viewType, mPresenter)
        }
    }

    override fun converDataToList(data: Any?): List<Any>? {
        var list = ArrayList<Any>()
        list.add("")
        (0..5).forEach { list.add(it) }
        return list
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (activity as MainActivity).showHomeTitle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showHomeTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /*override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showHomeTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    *//**
     * 初始化轮播图数据
     *//*
    fun setLunboData() {
        var images: ArrayList<String> = ArrayList()
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=827c5986c6ecc778ed7ac8a65f0e0139&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2F9ecb5c0b6dd4471000559917b2c56d58.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515562245761&di=f0528f7502f46366496a4168fc1d5cbe&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F03%2F48%2F3847e4a7a58e5644768bdc34a0f09148.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=12c10b2ac43a5194107a105ae2235cae&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F42%2F3c4a16697485ff3717379118de096f7e.jpg")
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()
    }

    *//**
     * 设置跑马灯公告
     *//*
    fun setActivityData() {
        mBinding.includeActivity.itemHomePostTv.isMarqueeEnable = true
    }

    override fun showErrorView() {
        showContentView()
    }*/
}