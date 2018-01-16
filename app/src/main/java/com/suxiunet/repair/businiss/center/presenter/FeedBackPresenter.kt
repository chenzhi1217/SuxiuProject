package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.text.TextUtils
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.FeedBackContract
import com.suxiunet.repair.businiss.center.proxy.SuggestionsProxy
import com.suxiunet.repair.businiss.center.request.SuggestionsRequest
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
class FeedBackPresenter(activity: Activity, view: FeedBackContract.View, model: FeedBackContract.Model) : BasicPresenter<FeedBackContract.View, FeedBackContract.Model>(activity, view, model) {

    var mProxy =  SuggestionsProxy(mActivity)
    var mRequest = SuggestionsRequest()

    /**
     * 提交意见反馈
     */
    fun subFeedBack(content: String) {
        //对数据进行判空
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showToast("请输入反馈内容")
            return
        }
        mRequest.suggestions = content
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        mRequest.loginId = userInfo?.loginId ?: ""
        
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<SuggestionsRequest,Any>{
            override fun onLoadSuccess(req: SuggestionsRequest?, type: BasicProxy.ProxyType, data: Any?) {
                mView.submitSuccess()
            }

            override fun onLoadError(req: SuggestionsRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.submitError(e)
            }

        })
        mProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
    }
}