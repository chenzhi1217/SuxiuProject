package com.suxiunet.repair.businiss.center.contract

import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
interface FeedBackContract {
    interface View: IView{
        fun submitSuccess()
        fun submitError(e: ApiException?)
    }
    interface Model: IModel
}