package com.suxiunet.repair.base

import android.content.Context
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.Utils
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   : 所有请求代理的基类
 */
abstract class BasicProxy<REQUEST,DATA> {

    constructor(context: Context)
    
    lateinit var mProxyCallBack: ProxyCallBack<REQUEST,DATA>

    enum class ProxyType {
        /**
         * 加载数据
         */
        LOAD_DATA,
        /**
         * 刷新数据
         */
        REFRESH_DATA,
        /**
         * 上拉加载数据
         */
        LOAD_MORE_DATA
    }

    /**
     * 注册代理请求的回调接口
     */
    fun setCallBack(callBack: ProxyCallBack<REQUEST, DATA>) {
        this.mProxyCallBack = callBack
    }

    /**
     * 网络请求的入口
     */
    open protected fun request(request: REQUEST,type: ProxyType) {
        //创建一个订阅者
        val basicSubscriber = BasicSubscriber(request, type)
        //检测当前网络是否可用
        val connecting = Utils.isNetworkConnectedOrConnecting(SuXiuApplication.appContext)
        if (!connecting) {
            basicSubscriber.onError(ApiException("当前网络不可用"))
            basicSubscriber.onCompleted()
        } else {
            bindData(basicSubscriber,request,type)
        }
    }

    /**
     * Rxjava结合Retrofit请求网路数据的核心
     */
    open protected fun bindData(subscriber: BasicSubscriber,request: REQUEST, type: ProxyType) {
        getObservable(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    /**
     * 让子类去获取Observable对象
     */
    abstract fun getObservable(request: REQUEST): Observable<DATA>

    interface ProxyCallBack<REQUEST,DATA>{
        /**
         * 网络请求成功的回调方法
         */
        fun onLoadSuccess(req: REQUEST?,type: ProxyType,data: DATA?)

        /**
         * 网络请求失败的回调方法
         */
        fun onLoadError(req:REQUEST?,type: ProxyType,e: ApiException?)
    }

    
    open inner class BasicSubscriber: Subscriber<DATA> {
        private var request: REQUEST? = null
        private var proxyType: ProxyType
        
        constructor(request: REQUEST,type: ProxyType) {
            this.request = request
            this.proxyType = type
        }
        
        fun isRefresh(): Boolean {
            return proxyType == ProxyType.REFRESH_DATA
        }
        
        fun isLoadData(): Boolean{
            return proxyType == ProxyType.LOAD_DATA
        }
        
        override fun onError(e: Throwable?) {
            try {
                if (mProxyCallBack != null) {
                    if (e is ApiException) {
                        mProxyCallBack.onLoadError(request, proxyType, e)
                    } else {
                        mProxyCallBack.onLoadError(request,proxyType,ApiException(e?.message))
                    }
                }
            } catch (e: Exception) {
                if (mProxyCallBack != null) {
                    mProxyCallBack.onLoadError(request,proxyType,ApiException(e?.message))
                }
            }
        }

        override fun onCompleted() {
            unsubscribe()
        }

        override fun onNext(t: DATA) {
            try {
                if (mProxyCallBack != null) {
                    mProxyCallBack.onLoadSuccess(request,proxyType,t)
                }
            } catch (e: Exception) {
                if (mProxyCallBack != null) {
                    mProxyCallBack.onLoadError(request,proxyType,ApiException(e?.message))
                }
            }
        }
    }
}