package com.suxiunet.repair.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.Utils
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.baseui.SuXiuApplication
import com.suxiunet.repair.businiss.center.view.LoginActivity

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   : 添加了了加载中状态，并且控制了只能单个加载
 */
open abstract class RefreshProxy<REQUEST, DATA> : BasicProxy<REQUEST, DATA> {

    private var isLoading: Boolean = false
    private var loadingView: View? = null
    private var mContext: Context?

    constructor(context: Context) : super(context){
        this.mContext = context
    }

    public override fun request(request: REQUEST, type: ProxyType) {
        //创建一个订阅者
        val basicSubscriber = RefreshSubscriber(request, type)
        //检测当前网络是否可用
        val connecting = Utils.isNetworkConnectedOrConnecting(SuXiuApplication.appContext)
        if (!connecting) {
            basicSubscriber.onError(ApiException("当前网络不可用"))
            basicSubscriber.onCompleted()
        } else {
            bindData(basicSubscriber, request, type)
        }
    }

    override fun bindData(subscriber: BasicSubscriber, request: REQUEST, type: ProxyType) {
        if (isLoading) {
            return
        }
        //显示加载中弹框
        switchLoadingView(subscriber.isLoadData(), true)
        super.bindData(subscriber, request, type)
        isLoading = false
    }

    fun isLoading(): Boolean {
        return isLoading
    }


    inner class RefreshSubscriber : BasicSubscriber {

        constructor(request: REQUEST, type: ProxyType) : super(request, type)

        override fun onError(e: Throwable?) {
            isLoading = false
            switchLoadingView(isLoadData(), false)
            super.onError(e)
            checkUnLoad(e)
        }

        override fun onNext(t: DATA) {
            isLoading = false
            switchLoadingView(isLoadData(), false)
            super.onNext(t)
        }
    }

    /**
     * 检测是否登录
     */
    private fun checkUnLoad(e: Throwable?) {
        if (e is ApiException) {
            if (e.displayCode == 405) {
                //跳转到登录界面
                var intent = Intent(SuXiuApplication.appContext, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext?.startActivity(intent)

                if (mContext != null && mContext is Activity && mContext !is MainActivity) {
                    (mContext as Activity).finish()
                } 
            }
        }
    }

    /**
     * 设置加载中弹框
     */
    fun setLoadingView(view: View) {
        this.loadingView = view
    }

    /**
     * 控制加载中弹框的显示预隐藏
     */
    fun switchLoadingView(isLoadData: Boolean, switch: Boolean) {
        if (isLoadData) {
            loadingView?.visibility = if (switch) View.VISIBLE else View.GONE
        }
    }
}