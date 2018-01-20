package com.suxiunet.repair.base.baseui

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicRequest
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.base.IPresenter
import com.suxiunet.repair.base.adapter.BaseRecyclerViewAdapter
import com.suxiunet.repair.databinding.FragRecyclerviewBasicBinding
import com.suxiunet.repair.util.ToastUtil
import com.suxiunet.repair.view.CustomRecyclerView

/**
 * author : chenzhi
 * time   : 2018/01/06
 * desc   : 所有列表页面的基类
 */
abstract class BasicRecyclerViewFragment<REQUEST : BasicRequest, PRESENT: IPresenter, DATA,ITEM >: BasicFragment<REQUEST, PRESENT, DATA, FragRecyclerviewBasicBinding>() {

    lateinit var mRecyclerView: CustomRecyclerView
    lateinit var mAdapter: BaseRecyclerViewAdapter<ITEM>
    lateinit var mRecyclerViewManager: LinearLayoutManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        //设置下拉刷新
        //TODO 这里执行下拉刷新的逻辑
        mParentBinding.sfBasicFrag.setOnRefreshListener { refreshData() }
        //设置空布局页面
        mBinding.includeBasicEmptyLayout.ivEmpty.setImageResource(setEmptyBgResId())
        mBinding.includeBasicEmptyLayout.tvEmpty.text = getString(setEmptyInfoResId())
        //设置RecyclerView
        mRecyclerView = mBinding.rvBasicRv
        mRecyclerView.setEmptyView(mBinding.includeBasicEmptyLayout.root)
        mAdapter = onCreateAdapter()
        mRecyclerView.layoutManager = onCreateLayoutManager()
        
        mAdapter.setViewTypeMapper(onCreateViewTypeMapper())
        
        //设置上拉加载
        if (pageEnable()) {
            //TODO pageSize应该抽到常量类中
            mAdapter.setLoadMoreView(getLoadMoreLoadingLayoutId(), getLoadMoreRetryLayoutId(), getLoadMoreNoDataLayoutId(), 1, 15, object : BaseRecyclerViewAdapter.LoadMoreCallback() {
                override fun onLoadMore() {
                    loadMoreData()
                }
            })
        }
    }

    abstract fun initView()

    abstract fun onCreateViewTypeMapper(): BaseRecyclerViewAdapter.ViewTypeMapper<ITEM>?


    /**
     * 上拉加载的逻辑
     */
    private fun loadMoreData() {
        if (!mDataProxy.isLoading() && pageEnable()) {
            //TODO 
            mDataProxy.request(getRequestData(), BasicProxy.ProxyType.LOAD_MORE_DATA)
        }
    }

    /**
     * 下拉刷新的逻辑
     */
    private fun refreshData() {
        if (pageEnable()) {
            //重置上拉加载布局和请求对象
            mAdapter.reSetLoadMoreState()
            getRequestData().reSetCurPage()
        }
        if (!mDataProxy.isLoading() && needNet()) {
            //TODO 这里要拦截屏幕的触摸事件
            mDataProxy.request(getRequestData(), BasicProxy.ProxyType.REFRESH_DATA)
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
    private fun onCreateAdapter(): BaseRecyclerViewAdapter<ITEM> {
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
    open protected fun setEmptyBgResId(): Int {
        return R.mipmap.icon_empty_common
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

    override fun onCreateDataCallBack(): BasicProxy.ProxyCallBack<REQUEST, DATA> {
        return LoadMoreCallBack()
    }

    /**
     * 网络请求的结果回掉
     */
    inner class LoadMoreCallBack: BasicDataCallBack() {
        override fun onLoadSuccess(req: REQUEST?, type: BasicProxy.ProxyType, data: DATA?) {
            if (pageEnable()) {
                getRequestData().pgDown()
            }
            if (type == BasicProxy.ProxyType.LOAD_MORE_DATA) {
                onLoadMoreSuccess(req,data)
            } else {
                super.onLoadSuccess(req, type, data)
            }
        }

        override fun onLoadError(req: REQUEST?, type: BasicProxy.ProxyType, e: ApiException?) {
            if (type == BasicProxy.ProxyType.LOAD_MORE_DATA) {
                loadMoreError(req,e)
            } else {
                super.onLoadError(req, type, e)
            }
        }
    }

    /**
     * 上拉加载失败
     */
    private fun loadMoreError(req: REQUEST?, e: ApiException?) {
        ToastUtil.showToast(e?.message?:"网络异常")
        mAdapter.setLoadMoreFailed()
    }

    /**
     * 上拉加载成功
     */
    private fun onLoadMoreSuccess(req: REQUEST?, data: DATA?) {
        var loadMoreData = converDataToList(data)
        if (loadMoreData != null && loadMoreData.size > 0) {
            mAdapter.dataList.addAll(loadMoreData)
        }
        checkIsLastPage(loadMoreData)
        mAdapter.notifyDataSetChanged()
    }

    /**
     * 首次加载数据失败
     */
    override fun onLoadDataError(req: REQUEST?, e: ApiException?) {
        super.onLoadDataError(req, e)
    }
    
    /**
     * 首次加载数据成功
     */
    override fun onLoadDataSuccess(req: REQUEST?, data: DATA?) {
        super.onLoadDataSuccess(req, data)
        setInitData(data)
    }

    /**
     * 下拉刷新失败
     */
    override fun onRefreshDataError(req: REQUEST?, e: ApiException?) {
        super.onRefreshDataError(req, e)
        ToastUtil.showToast(e?.message?:"网络异常")
    }

    /**
     * 下拉刷新成功
     */
    override fun onRefreshDataSuccess(req: REQUEST?, data: DATA?) {
        super.onRefreshDataSuccess(req, data)
        setInitData(data)
    }

    /**
     * 将网络加载的数据设置到Adapter中去
     * @param data
     */
    protected fun setInitData(data: DATA?) {
        val items = converDataToList(data)
        if (mRecyclerView.adapter == null) {
            mRecyclerView.adapter = mAdapter
        }
        mAdapter.setItems(items)

        //判断是否为最后一页
        checkIsLastPage(items)
        mAdapter.notifyDataSetChanged()
    }

    private fun checkIsLastPage(items: List<ITEM>?) {
        if (pageEnable()) {
            if (items == null || items?.size == 0) {
                mAdapter.setNoMoreData()
            } else {
                mAdapter.setLoadMoreFinished()
            }
        }
    }

    abstract fun converDataToList(data: DATA?): List<ITEM>?
}