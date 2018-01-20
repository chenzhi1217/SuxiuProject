package com.suxiunet.repair.businiss.order.orderhome.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.suxiunet.repair.R
import com.suxiunet.repair.base.adapter.CommonFragmentPageAdapter
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.order.orderhome.contract.OrderHomeContract
import com.suxiunet.repair.businiss.order.orderhome.model.OrderHomeModel
import com.suxiunet.repair.businiss.order.orderhome.presenter.OrderHomePresenter
import com.suxiunet.repair.businiss.order.orderlist.view.OrderListFragment
import com.suxiunet.repair.databinding.FragOrderHomeBinding

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   : 订单首页
 */
class OrderHomeFragment: NomalFragment<OrderHomePresenter,FragOrderHomeBinding>(),OrderHomeContract.View{
    lateinit var mTitles: ArrayList<String>
    lateinit var mFragments: ArrayList<Fragment>
    override fun initView() {
        //初始化标题
        initTitle()
        //初始化Fragment
        initFragment()

        mBinding.vpCommon.adapter = CommonFragmentPageAdapter(childFragmentManager, mTitles, mFragments)
        mBinding.tlCommon.setupWithViewPager(mBinding.vpCommon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).showOrderTitle()
    }
    

    override fun getPresenter(): OrderHomePresenter? {
        return OrderHomePresenter(activity,this,OrderHomeModel())
    }
    
    override fun getContentLayoutId(): Int {
        return R.layout.frag_order_home
    }
    
    private fun initFragment() {
        mFragments = ArrayList()
        mFragments.add(OrderListFragment("A"))
        mFragments.add(OrderListFragment("B"))
        mFragments.add(OrderListFragment("C"))
        mFragments.add(OrderListFragment("D"))
        mFragments.add(OrderListFragment("E"))
    }
    
    private fun initTitle() {
        mTitles = ArrayList()
        mTitles.add("待接单")
        mTitles.add("服务中")
        mTitles.add("待付款")
        mTitles.add("已完成")
        mTitles.add("已取消")
    }
    
    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            (activity as MainActivity).showOrderTitle()
        }
    }
}
