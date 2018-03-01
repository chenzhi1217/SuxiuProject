package com.suxiunet.repair.businiss.center.proxy

import android.content.Context
import com.suxiunet.data.api.UserApiImpl
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.repair.base.RefreshProxy
import com.suxiunet.repair.businiss.center.request.UserInfoRequest
import rx.Observable

/**
 * author : chenzhi
 * time   : 2018/02/02
 * desc   :
 */
class UpLoadImageProxy: RefreshProxy<UserInfoRequest, UserInfoEntity> {
  
    var mUserApiImpl: UserApiImpl
    constructor(context: Context): super(context){
        mUserApiImpl = UserApiImpl(context)
    }
    
    override fun getObservable(request: UserInfoRequest): Observable<UserInfoEntity> {
        return mUserApiImpl.upLoadImage(request.map,request.file)
                .map{it.retData}
    }
}