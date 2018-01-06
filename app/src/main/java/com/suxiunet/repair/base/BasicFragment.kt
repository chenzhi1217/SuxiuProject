package com.suxiunet.repair.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.R
import com.suxiunet.repair.databinding.FragBasicBinding

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   :
 */
abstract class BasicFragment<REQUEST : BaseRequest, PRESENT: IPresenter, DATA, BIND : ViewDataBinding> : OriginalFragment() {
    lateinit var mParentBinding: FragBasicBinding
    lateinit var mBinding: BIND
    var mPresenter: PRESENT? = null

    lateinit var mDataProxy: RefreshProxy<REQUEST, DATA>

    //加载中的view
    lateinit var mLoadingView: View
    //加载失败的view
    lateinit var mErrorView: View
    //加载成功的View
    lateinit var mContentView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (needNet()) {
            //创建一个代理对象
            mDataProxy = onCreateProxy()
            mDataProxy.setCallBack(onCreateDataCallBack())
        }
    }

    private fun onCreateDataCallBack(): BasicProxy.ProxyCallBack<REQUEST, DATA> {
        return BasicDataCallBack()
    }

    /**
     * 让子类返回网络请求代理对象
     */
    abstract fun onCreateProxy(): RefreshProxy<REQUEST, DATA>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //总的Binding
        mParentBinding = DataBindingUtil.inflate<FragBasicBinding>(inflater, R.layout.frag_basic, container, false)
        mErrorView = mParentBinding.includeCommonError.root
        mLoadingView = mParentBinding.includeCommonLoading.root
        //内容的Binding
        mBinding = DataBindingUtil.inflate<BIND>(inflater, getContentLayoutId(), container, false)
        mContentView = mBinding?.root
        mParentBinding.flBasicFrag.addView(mContentView)
        //设置失败页面
        mParentBinding?.includeCommonError?.ivNetErrorIcon?.setImageResource(getErrorIconResid())
        mParentBinding?.includeCommonError?.root?.setOnClickListener { initLoadData() }
        //设置SwifeRefresh
        mParentBinding.sfBasicFrag.isEnabled = setSwipeRefreshEnable()

        mPresenter = getPresenter()
        
        showContentView()
        
        

        return mParentBinding?.root
    }

    /**
     * 下拉刷新控件默认是不可用的
     */
    open protected fun setSwipeRefreshEnable(): Boolean {
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (needNet()) {
            initLoadData()
        }
    }

    /**
     * 加载初始化数据
     */
    private fun initLoadData() {
        showLoadingView()
        if (!mDataProxy.isLoading()) {
            mDataProxy.request(getRequestData(), BasicProxy.ProxyType.LOAD_DATA)
        }
    }

    /**
     * 让子类返回请求对象
     */
    abstract fun getRequestData(): REQUEST

    /**
     * 是否加载网络的开关
     */
    open protected fun needNet(): Boolean {
        return true
    }

    /**
     * 让子类创建Presenter
     */
    abstract fun getPresenter(): PRESENT?

    /**
     * 失败页面的icon
     */
    open protected fun getErrorIconResid(): Int {
        return R.mipmap.ic_launcher
    }

    /**
     * 让子类返回布局id
     */
    abstract fun getContentLayoutId(): Int

    /**
     * 显示加载成功的布局
     */
    protected fun showContentView() {
        switchView(mContentView, mErrorView, mLoadingView)
    }

    /**
     * 显示加载失败的布局
     */
    protected fun showErrorView() {
        switchView(mErrorView, mContentView, mLoadingView)
    }

    /**
     * 显示加载中的布局
     */
    protected fun showLoadingView() {
        switchView(mLoadingView, mContentView, mErrorView)
    }

    /**
     * 切换三种布局的显示预隐藏
     */
    private fun switchView(showView: View, vararg hideView: View) {
        if (hideView == null || hideView.size == 0) {
            return
        }
        showView.visibility = View.VISIBLE
        for (view in hideView) {
            view.visibility = View.GONE
        }
    }

    /**
     * 网路请求回调接口的实例
     */
    inner class BasicDataCallBack : BasicProxy.ProxyCallBack<REQUEST, DATA> {
        override fun onLoadSuccess(req: REQUEST?, type: BasicProxy.ProxyType, data: DATA?) {
            when (type) {
            //首次加载数据成功
                BasicProxy.ProxyType.LOAD_DATA -> {
                    loadDataSuccess(req, data)
                }
            //下拉刷新数据成功
                BasicProxy.ProxyType.REFRESH_DATA -> {
                    refreshDataSuccess(req, data)
                }
            }
        }

        override fun onLoadError(req: REQUEST?, type: BasicProxy.ProxyType, e: ApiException?) {
            when (type) {
                //首次加载数据失败
                BasicProxy.ProxyType.LOAD_DATA -> loadDataError(req, e)
                //下拉刷新数据失败
                BasicProxy.ProxyType.REFRESH_DATA -> refreshDataError(req, e)
            }
        }
    }

    /**
     * 下拉刷新数据失败
     */
    open protected fun refreshDataError(req: REQUEST?, e: ApiException?) {

    }

    /**
     * 加载数据失败
     */
    open protected fun loadDataError(req: REQUEST?, e: ApiException?) {
        showErrorView()
    }

    /**
     * 下拉刷新数据成功
     */
    open protected fun refreshDataSuccess(req: REQUEST?, data: DATA?) {

    }

    /**
     * 加载数据成功
     */
    open protected fun loadDataSuccess(req: REQUEST?, data: DATA?) {
        showContentView()
    }
}