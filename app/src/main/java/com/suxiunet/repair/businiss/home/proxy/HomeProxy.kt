package com.suxiunet.repair.businiss.home.proxy

import android.content.Context
import com.suxiunet.data.api.HomeApiImpl
import com.suxiunet.data.entity.base.ApiResponse
import com.suxiunet.data.entity.user.HomeEntity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.request.HomeRequest
import rx.Observable
import rx.functions.Func1

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页网络请求的代理对象
 */
class HomeProxy(var context: Context): RefreshProxy<HomeRequest, HomeEntity>(context) {
    override fun getObservable(request: HomeRequest): Observable<HomeEntity> {
        return HomeApiImpl(context).fetchHomeData().map { t -> t?.retData?:HomeEntity() }
    }
}