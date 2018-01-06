package com.suxiunet.repair.base

import android.content.ClipData
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.databinding.FragRecyclerviewBasicBinding
import com.suxiunet.repair.view.CustomRecyclerView

/**
 * author : chenzhi
 * time   : 2018/01/06
 * desc   : 所有列表页面的基类
 */
abstract class BasicRecyclerViewFragment<REQUEST : BaseRequest, PRESENT: IPresenter, DATA,ITEM >: BasicFragment<REQUEST, PRESENT, DATA, FragRecyclerviewBasicBinding>() {

    lateinit var mRecyclerView: CustomRecyclerView
    lateinit var mAdapter: BaseRecyclerViewAdapter<ITEM>
    lateinit var mRecyclerViewManager: LinearLayoutManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置下拉刷新
        //TODO 这里执行下拉刷新的逻辑
        mParentBinding.sfBasicFrag.setOnRefreshListener {  }
        mParentBinding.sfBasicFrag.setColorSchemeResources(R.color.color_red)
        //设置空布局页面
        mBinding.includeBasicEmptyLayout.ivEmpty.setImageResource(setEmptyBgResId())
        mBinding.includeBasicEmptyLayout.tvEmpty.text = getString(setEmptyInfoResId())
        //设置RecyclerView
        mRecyclerView = mBinding.rvBasicRv
        mRecyclerView.setEmptyView(mBinding.includeBasicEmptyLayout.root)
        mAdapter = onCreateAdapter()
        mRecyclerView.layoutManager = onCreateLayoutManager()

        //设置上拉加载
        if (pageEnable()) {
            //TODO pageSize应该抽到常量类中
            mAdapter.setLoadMoreView(getLoadMoreLoadingLayoutId(), getLoadMoreRetryLayoutId(), getLoadMoreNoDataLayoutId(), 1, 15, object : BaseRecyclerViewAdapter.LoadMoreCallback() {
                override fun onLoadMore() {
//                    loadMoreData()
                }
            })
        }
    }

    /**
     * 返回上拉加载过程中空数据的布局
     */
    private fun getLoadMoreNoDataLayoutId(): Int {
        return R.layout.loadmore_empty
    }

    /**
     * 返回上拉加载过程中加载失败的布局
     */
    private fun getLoadMoreRetryLayoutId(): Int {
        return R.layout.loadmore_error
    }

    /**
     * 返回上拉加载过程中加载中的布局
     */
    private fun getLoadMoreLoadingLayoutId(): Int {
        return R.layout.loadmore_loading
    }

    /**
     * 是否需要分页加载的开关，默认开启
     */
    open protected fun pageEnable(): Boolean {
        return true
    }

    /**
     * 创建RecyclerView的Manager
     */
    private fun onCreateLayoutManager(): RecyclerView.LayoutManager? {
        val recyclerColumn = getRecyclerColumn()
        if (recyclerColumn == 1) {
            mRecyclerViewManager = LinearLayoutManager(context)
        } else {
            val gm = GridLayoutManager(context, recyclerColumn)
            gm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (mAdapter.isLoadMoreItem(position)) {
                        return recyclerColumn
                    } else {
                        return this@BasicRecyclerViewFragment.getItemColumnSize(mAdapter.getObject(position), position)
                    }
                }
            }
            mRecyclerViewManager = gm
        }
        return mRecyclerViewManager
    }

    /**
     * 子类可以重写此方法来决定当前条目所占的列数
     */
    open protected fun getItemColumnSize(item: ITEM, position: Int): Int {
        return getRecyclerColumn()
    }

    /**
     * 获取当前RecyclerView的列数，默认情况为1列
     */
    open protected fun getRecyclerColumn(): Int {
        return 1
    }

    /**
     * 创建RecycleView的Adapter
     */
    private fun onCreateAdapter():BaseRecyclerViewAdapter<ITEM> {
        return object : BaseRecyclerViewAdapter<ITEM>(mRecyclerView) {
            override fun onCreateItemHolder(parent: ViewGroup?, viewType: Int): BasicHolder<out ITEM, out ViewDataBinding> {
                return this@BasicRecyclerViewFragment.onCreateViewHolder(parent,viewType)
            }
        }
    }

    /**
     * 让子类根据当前条目创建对应的Adapter
     */
    abstract fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasicHolder<out ITEM, out ViewDataBinding>

    /**
     * 空布局时显示的提示
     */
    private fun setEmptyInfoResId(): Int {
        return R.string.data_is_empty
    }

    /**
     * 空布局时显示的icon
     */
    private fun setEmptyBgResId(): Int {
        return R.mipmap.ic_launcher
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_recyclerview_basic
    }

    /**
     * 默认开启下拉刷新控件
     */
    override fun setSwipeRefreshEnable(): Boolean {
        return true
    }
}