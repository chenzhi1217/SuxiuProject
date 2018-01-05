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
 * desc   : 不需要网络请求Fragment的基类
 */
abstract class NomalFragment<PRESENT: IPresenter,BIND : ViewDataBinding> : OriginalFragment() {
    lateinit var mBinding: BIND
    var mPresenter: PRESENT? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //内容的Binding
        mBinding = DataBindingUtil.inflate<BIND>(inflater, getContentLayoutId(), container, false)
        mPresenter = getPresenter()
        initView()
        return mBinding.root
    }

    abstract fun initView()

    /**
     * 让子类创建Presenter
     */
    abstract fun getPresenter(): PRESENT?

    /**
     * 让子类返回布局id
     */
    abstract fun getContentLayoutId(): Int
}